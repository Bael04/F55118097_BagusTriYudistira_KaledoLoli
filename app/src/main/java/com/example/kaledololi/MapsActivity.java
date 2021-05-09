package com.example.kaledololi;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double LatStart = -0.8004534060443796;
        double LngStart = 119.80335969523875;
        double LatEnd = -0.793346118638418;
        double LngEnd = 119.80003373938818;

        // Add a marker in Rumah Sakit and move the camera
        LatLng RumahBagus = new LatLng(LatStart, LngStart);
        LatLng RumahmMakanKaledo = new LatLng(LatEnd, LngEnd);

        mMap.addPolyline(new PolylineOptions().add(
                RumahBagus,
                new LatLng(-0.8004535549556979, 119.80355773681624),
                new LatLng(-0.7982805889467266, 119.80237941772161),
                new LatLng(-0.793481433257627, 119.79982623708273),
                new LatLng(-0.7933822025372927, 119.80004276122754),
                RumahmMakanKaledo).width(10).color(Color.GREEN)
        );

        mMap.addMarker(new MarkerOptions().position(RumahBagus).title("Rumah Bagus").snippet("Rumahku").icon(bitmapDescriptorFromImage(getApplicationContext(), R.mipmap.logo)));
        mMap.addMarker(new MarkerOptions().position(RumahmMakanKaledo).title("Kaledo Loli").snippet("Kaledo Loli").icon(bitmapDescriptorFromImage(getApplicationContext(), R.mipmap.logokaledo)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RumahBagus, 13.5f));

        //Menghitung Jarak Antar Titik
        float[] results = new float[1];
        Location.distanceBetween(LatStart,LngStart,LatEnd,LngEnd, results);
        float distance = results[0];

        int kilometer = (int) (distance/1000);
        Toast.makeText(this,String.valueOf(kilometer)+"Km.", Toast.LENGTH_LONG).show();
    }

    private BitmapDescriptor bitmapDescriptorFromImage(Context context, int ImageResId)
    {
        Drawable imageDrawable = ContextCompat.getDrawable(context, ImageResId);
        imageDrawable.setBounds(0,0,imageDrawable.getIntrinsicWidth(),imageDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(imageDrawable.getIntrinsicWidth(),imageDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        imageDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}