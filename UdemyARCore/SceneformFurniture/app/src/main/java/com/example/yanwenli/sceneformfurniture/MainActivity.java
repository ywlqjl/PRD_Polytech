package com.example.yanwenli.sceneformfurniture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {

    private ArFragment fragment;
    private String selectedObjet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        initGallery();

        fragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    Log.d("Model" , selectedObjet);
                }
        );

    }

    private void initGallery(){
        LinearLayout gallery = findViewById(R.id.gallery_layout);

        ImageView chair = new ImageView(this);
        ImageView couch = new ImageView(this);
        ImageView lamp = new ImageView(this);

//        chair.setImageResource(R.drawable.chair);
//        couch.setImageResource(R.drawable.couch);
//        lamp.setImageResource(R.drawable.lamp);
//
//        chair.setContentDescription("Chair");
//        couch.setContentDescription("Couch");
//        lamp.setContentDescription("Lamp");

        chair.setOnClickListener(view -> {selectedObjet = "Chair";});
        couch.setOnClickListener(view -> {selectedObjet = "Couch";});
        lamp.setOnClickListener(view -> {selectedObjet = "Lamp";});
//
//        gallery.addView(chair);
//        gallery.addView(couch);
//        gallery.addView(lamp);

    }
}
