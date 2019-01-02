package com.example.yanwenli.capteurdirection;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.*;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import java.nio.ByteBuffer;
import java.util.*;

public class MyCamera extends AppCompatActivity
{
    private TextureView mTextureView;
    private String mCameraId;
    private Size mPreviewSize;
    private CameraDevice mCameraDevice;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private CaptureRequest mCaptureRequest;
    private CameraCaptureSession mPreviewSession;
    private ImageReader mImageReader;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);
        mTextureView = findViewById(R.id.ttv_camera);
        
    }
    
    @Override
    protected void onResume()
    {
        super.onResume();
        mTextureView.setSurfaceTextureListener(textureListener);
    
    }
    
    /**
     * 当SurefaceTexture准备好后会回调SurfaceTextureListener 的onSurfaceTextureAvailable()方法
     */
    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener()
    {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height)
        {
            //当SurefaceTexture可用的时候，设置相机参数并打开相机
            setupCamera(width, height);
            openCamera();
        }
        
        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
        {
        
        }
        
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface)
        {
            return false;
        }
        
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface)
        {
        
        }
    };
    
    /**
     * 为了更好地预览，我们根据TextureView的尺寸设置预览尺寸，Camera2中使用CameraManager来管理摄像头
     *
     * @param width
     * @param height
     */
    private void setupCamera(int width, int height)
    {
        //获取摄像头的管理者CameraManager
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try
        {
            //遍历所有摄像头
            for(String cameraId : manager.getCameraIdList())
            {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                //默认打开后置摄像头
                if(characteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT)
                    continue;
                //获取StreamConfigurationMap，它是管理摄像头支持的所有输出格式和尺寸
                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                //根据TextureView的尺寸设置预览尺寸
                mPreviewSize = getOptimalSize(map.getOutputSizes(SurfaceTexture.class), width, height);
    
                setupImageReader();
    
                mCameraId = cameraId;
                break;
            }
        }
        catch(CameraAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    //选择sizeMap中大于并且最接近width和height的size
    private Size getOptimalSize(Size[] sizeMap, int width, int height)
    {
        List<Size> sizeList = new ArrayList<>();
        for(Size option : sizeMap)
        {
            if(width > height)
            {
                if(option.getWidth() > width && option.getHeight() > height)
                {
                    sizeList.add(option);
                }
            }
            else
            {
                if(option.getWidth() > height && option.getHeight() > width)
                {
                    sizeList.add(option);
                }
            }
        }
        if(sizeList.size() > 0)
        {
            return Collections.min(sizeList, new Comparator<Size>()
            {
                @Override
                public int compare(Size lhs, Size rhs)
                {
                    return Long.signum(lhs.getWidth() * lhs.getHeight() - rhs.getWidth() * rhs.getHeight());
                }
            });
        }
        return sizeMap[0];
    }
    
    private void openCamera()
    {
        //获取摄像头的管理者CameraManager
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        //检查权限
        try
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
            //打开相机，第一个参数指示打开哪个摄像头，第二个参数stateCallback为相机的状态回调接口，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            manager.openCamera(mCameraId, stateCallback, null);
        }
        catch(CameraAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback()
    {
        @Override
        public void onOpened(CameraDevice camera)
        {
            mCameraDevice = camera;
            //开启预览
            
            startPreview();
        }
        
        @Override
        public void onDisconnected(@NonNull CameraDevice camera)
        {
        
        }
        
        @Override
        public void onError(@NonNull CameraDevice camera, int error)
        {
        
        }
    };
    
    private void startPreview()
    {
        SurfaceTexture mSurfaceTexture = mTextureView.getSurfaceTexture();
        //设置TextureView的缓冲区大小
        mSurfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
        //获取Surface显示预览数据
        Surface mSurface = new Surface(mSurfaceTexture);
        try
        {
            //创建CaptureRequestBuilder，TEMPLATE_PREVIEW比表示预览请求
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //设置Surface作为预览数据的显示界面
            mCaptureRequestBuilder.addTarget(mSurface);
            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            mCameraDevice.createCaptureSession(Arrays.asList(mSurface), new CameraCaptureSession.StateCallback()
            {
                @Override
                public void onConfigured(CameraCaptureSession session)
                {
                    try
                    {
                        //创建捕获请求
                        mCaptureRequest = mCaptureRequestBuilder.build();
                        mPreviewSession = session;
                        //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                        mPreviewSession.setRepeatingRequest(mCaptureRequest, null, null);
                    }
                    catch(CameraAccessException e)
                    {
                        e.printStackTrace();
                    }
                }
                
                @Override
                public void onConfigureFailed(CameraCaptureSession session)
                {
                
                }
            }, null);
        }
        catch(CameraAccessException e)
        {
            e.printStackTrace();
        }
    }
    
    private void setupImageReader()
    {
        //前三个参数分别是需要的尺寸和格式，最后一个参数代表每次最多获取几帧数据，本例的2代表ImageReader中最多可以获取两帧图像流
        mImageReader = ImageReader.newInstance(mPreviewSize.getWidth(), mPreviewSize.getHeight(), ImageFormat.JPEG, 2);
        //监听ImageReader的事件，当有图像流数据可用时会回调onImageAvailable方法，它的参数就是预览帧数据，可以对这帧数据进行处理
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener()
        {
            @Override
            public void onImageAvailable(ImageReader reader)
            {
                Image image = reader.acquireLatestImage();
                //我们可以将这帧数据转成字节数组，类似于Camera1的PreviewCallback回调的预览帧数据
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                image.close();
            }
        }, null);
    }
    
    
    

}
