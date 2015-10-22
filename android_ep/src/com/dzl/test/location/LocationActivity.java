package com.dzl.test.location;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dzl.test.BaseActivity;
import com.dzl.test.R;
  
/** 
 * @author Geek_Soledad (66704238@51uc.com) 
 */  
public class LocationActivity extends BaseActivity {  
    private double mLongitude;  
    private double mLatitude;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_location);  
    }  
  
    public void onButtonClick(View v) {  
        switch (v.getId()) {  
        case R.id.get_location:  
            getLocation();  
            break;  
        case R.id.query:  
            Toast.makeText(this,  
                    LocationUtil.getAddress(mLatitude, mLongitude), 1000)  
                    .show();  
            break;  
        default:  
            break;  
        }  
    }  
  
    private void getLocation() {  
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        locationManager.requestLocationUpdates(  
                LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {  
                    @Override  
                    public void onStatusChanged(String provider, int status,  
                            Bundle extras) {  
                    }  
  
                    @Override  
                    public void onProviderEnabled(String provider) {  
                        Toast.makeText(LocationActivity.this, "enable", 1000)  
                                .show();  
                        System.out.println("enable");  
                    }  
  
                    @Override  
                    public void onProviderDisabled(String provider) {  
                        Toast.makeText(LocationActivity.this, "disable", 1000)  
                                .show();  
                        System.out.println("disable");  
                    }  
  
                    @Override  
                    public void onLocationChanged(Location location) {  
                        mLatitude = location.getLatitude();  
                        mLongitude = location.getLongitude();  
                        System.out.println(location.getLatitude());  
                        System.out.println(location.getLongitude());  
                        Toast.makeText(LocationActivity.this,  
                                mLatitude + ":" + mLongitude, 1000).show();  
                    }  
                });  
    }  
}  