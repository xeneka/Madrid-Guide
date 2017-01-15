package es.elprincipe.madridguide.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.fragments.ActivitiesFragment;
import es.elprincipe.madridguide.interactor.activity.GetActivitiesByNameInteractor;
import es.elprincipe.madridguide.interactor.activity.GetAllActivitiesFromLocalCacheInteractor;
import es.elprincipe.madridguide.manager.db.ActivityDAO;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.navigator.Navigator;
import es.elprincipe.madridguide.view.OnElementClick;

public class ActivitiesActivity extends AppCompatActivity implements  GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleMap.InfoWindowAdapter {


    @BindView(R.id.toolbar_activity_activities)
    Toolbar toolbar;

    @BindView(R.id.toolbar_activities_editSearch)
    TextView toolbarEdit;

    private ActivitiesFragment activitiesFragment;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private List<Marker> marker = new LinkedList<>();



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
                Log.v(getClass().getName(),v.getText().toString());
                hideSoftKeyboard();

                GetActivitiesByNameInteractor filterActivityInteractor = new GetActivitiesByNameInteractor(v.getText().toString());
                filterActivityInteractor.execute(ActivitiesActivity.this, new GetActivitiesByNameInteractor.GetActivitiesByNameInteractorResponse() {
                    @Override
                    public void response(Activities activities) {
                        updateUI(activities);

                    }
                });


                return false;
            }
        });





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // MenuInflater inflater = getMenuInflater();
       // inflater.inflate(R.menu.menu_search, menu);
        // Associate searchable configuration with the SearchView
       /* SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search_activity).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        //SearchView searchView =*/

        Log.v(getClass().getName(), "*****");

        return true;
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


            }
        });




    }

    private void updateUI(Activities activities) {
        if (marker.size()>0) {
            for (Marker markactivity : marker) {
                markactivity.remove();
            }
        }
        activitiesFragment.setActivities(activities);
        putMarkerInMap(activities);
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
                Marker markeractivity = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(activity.getLatitude(), activity.getLongitude()))
                        .title(activity.getName()));
                marker.add(markeractivity);


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
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
