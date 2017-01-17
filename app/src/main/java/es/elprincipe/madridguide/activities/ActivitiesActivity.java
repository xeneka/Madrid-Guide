package es.elprincipe.madridguide.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.adapter.MapInfoWindowAdapter;
import es.elprincipe.madridguide.fragments.ActivitiesFragment;
import es.elprincipe.madridguide.interactor.activity.GetActivitiesByNameInteractor;
import es.elprincipe.madridguide.interactor.activity.GetAllActivitiesFromLocalCacheInteractor;
import es.elprincipe.madridguide.manager.db.ActivityDAO;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.navigator.Navigator;
import es.elprincipe.madridguide.view.OnElementClick;

public class ActivitiesActivity extends AppCompatActivity implements OnMapReadyCallback {


    private static final int PERMISSION_FOR_LOCATION = 0;
    @BindView(R.id.toolbar_activity_activities)
    Toolbar toolbar;

    @BindView(R.id.toolbar_activities_editSearch)
    TextView toolbarEdit;

    private ActivitiesFragment activitiesFragment;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private List<Marker> marker = new LinkedList<>();
    private Activities activitiesDetail;
    private Hashtable<String, Boolean> markerSet;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);


        activitiesFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_fragment_activities);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapactivities);
        mapFragment.getMapAsync(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        loadActivitiesFromCache();

        toolbarEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                hideSoftKeyboard();

                GetActivitiesByNameInteractor filterActivityInteractor = new GetActivitiesByNameInteractor(v.getText().toString());
                filterActivityInteractor.execute(ActivitiesActivity.this, new GetActivitiesByNameInteractor.GetActivitiesByNameInteractorResponse() {
                    @Override
                    public void response(Activities activities) {
                        updateUI(activities);
                        activitiesDetail = activities;

                    }
                });


                return false;
            }
        });


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

                updateUI(activities);
                activitiesDetail = activities;


            }
        });


    }

    private void updateUI(Activities activities) {
        if (marker.size() > 0) {
            for (Marker markactivity : marker) {
                markactivity.remove();
            }
        }
        activitiesFragment.setActivities(activities);
        putMarkerInMap(activities);
    }

    private void configMap() {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(40.4168,-3.7038);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,13.0f));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, R.string.permissionforlocation, Toast.LENGTH_LONG).show();

            showPermissionDialog();

            return;
        }

        googleMap.setMyLocationEnabled(true);





    }

    public Activities getActivities() {

        ActivityDAO dao = new ActivityDAO(this);
        List<Activity> activyList = dao.query();
        Activities activities = Activities.build(activyList);
        return activities;
    }

    public void putMarkerInMap(Activities activities){
        markerSet = new Hashtable<>();
        for (Activity activity: activities.allActivities()) {
            if(activity.getLatitude()>0) {
                Marker markeractivity = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(activity.getLatitude(), activity.getLongitude()))
                        .title(activity.getName())
                        .snippet(String.valueOf(activities.indexOf(activity)))
                );
                marker.add(markeractivity);
                markerSet.put(markeractivity.getId(), false);


            }
        }

        googleMap.setInfoWindowAdapter(new MapInfoWindowAdapter(this,markerSet,activities,googleMap));
    }





    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        configMap();

    }
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    private void showPermissionDialog() {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_FOR_LOCATION);
        }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_FOR_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        this.googleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
