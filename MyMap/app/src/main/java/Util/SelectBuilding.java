package Util;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;

import java.util.ArrayList;


/**
 * Created by liujiang on 2016/5/31.
 */
public class SelectBuilding {
    GeocodeSearch search;
    //查询相关点的建筑
    public ArrayList getBuidings(FindLocation findLocation){
        ArrayList buldings = new ArrayList();
        //遍历所有此时的经度点
        for(int i = 0;i<=findLocation.getNowLngs().size();i++){
            String lng_n = findLocation.getNowLngs().get(i).toString();
            String lat_n = findLocation.getNowLats().get(i).toString();
            if(lng_n.equals("")||lat_n.equals("")){
                buldings.add("");
            }else{
                search .getFromLocationAsyn(new RegeocodeQuery(
                        new LatLonPoint(Double.parseDouble(lat_n)
                                ,Double.parseDouble(lng_n))
                        ,10 //半径
                        , GeocodeSearch.GPS));
            }

        }
        return buldings;
    }

}
