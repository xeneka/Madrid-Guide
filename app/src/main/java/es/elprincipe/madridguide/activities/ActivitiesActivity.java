package es.elprincipe.madridguide.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.fragments.ActivitiesFragment;
import es.elprincipe.madridguide.interactor.activity.GetAllActivitiesFromLocalCacheInteractor;
import es.elprincipe.madridguide.manager.db.ActivityDAO;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.navigator.Navigator;
import es.elprincipe.madridguide.view.OnElementClick;

public class ActivitiesActivity extends AppCompatActivity implements  GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    private ActivitiesFragment activitiesFragment;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);


        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_fragment_activities);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapactivities);
        mapFragment.getMapAsync(this);
        loadActivitiesFromCache();


    }

    private void loadActivitiesFromCache() {
        GetAllActivitiesFromLocalCacheInteractor interactor = new GetAllActivitiesFromLocalCacheInteractor();

        interactor.execute(this, new GetAllActivitiesFromLocalCacheInteractor.GetAllActivitiesFromLocalCacheInteractorResponse() {
            @Override
            public void response(final Activities activities) {

                activitiesFragment.setListener(new OnElementClick<Activity>() {
                    @Override
                    public void clickedOn(Activity activity, int position) {
                        Navigator.navigateFromActivitiesActivityToActivityDetail(ActivitiesActivity.this, activity);
                    }
                });

                activitiesFragment.setActivities(activities);
                putMarkerInMap(activities);

            }
        });




    }

    private void configMap() {
        //googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(40.4168,-3.7038);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,13.0f));
        //googleMap.setOnMarkerClickListener(this);
        googleMap.setInfoWindowAdapter(this);


    }

    public Activities getActivities() {

        ActivityDAO dao = new ActivityDAO(this);
        List<Activity> activyList = dao.query();
        Activities activities = Activities.build(activyList);
        return activities;
    }

    public void putMarkerInMap(Activities activities){
        for (Activity activity: activities.allActivities()) {
            if(activity.getLatitude()>0) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(activity.getLatitude(), activity.getLongitude()))
                        .title(activity.getName()));
            }
        }
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        configMap();
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //googleMap.setMyLocationEnabled(true);
        //map.setTrafficEnabled(true);
        //map.setIndoorEnabled(true);
        //map.setBuildingsEnabled(true);
        //map.getUiSettings().setZoomControlsEnabled(true);
    }
}
