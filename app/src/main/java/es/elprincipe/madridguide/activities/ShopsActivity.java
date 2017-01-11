package es.elprincipe.madridguide.activities;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.fragments.ShopsFragment;
import es.elprincipe.madridguide.interactor.shop.GetAllShopsFromLocalCacheInteractor;
import es.elprincipe.madridguide.manager.db.DBShopConstants;
import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.manager.db.provider.MadridGuideProvider;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.navigator.Navigator;
import es.elprincipe.madridguide.view.OnElementClick;


public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleMap.InfoWindowAdapter {


    private ShopsFragment shopsFragment;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        loadShopFromCache();


        //LoaderManager loaderManager = getSupportLoaderManager();
        //loaderManager.initLoader(0,null,this);

    }

    private void loadShopFromCache() {
        GetAllShopsFromLocalCacheInteractor interactor = new GetAllShopsFromLocalCacheInteractor();
        interactor.execute(this, new GetAllShopsFromLocalCacheInteractor.OnGetAllShopsFromLocalCacheInteractorCompletion() {
            @Override
            public void completion(Shops shops) {

                shopsFragment.setListener(new OnElementClick<Shop>() {
                    @Override
                    public void clickedOn(Shop shop, int position) {
                        Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
                    }
                });
                shopsFragment.setShops(shops);
                putMarkerInMap(shops);
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

    public Shops getShops() {

        ShopDAO dao = new ShopDAO(this);
        List<Shop> shopList = dao.query();
        Shops shops = Shops.build(shopList);
        return shops;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this, MadridGuideProvider.SHOPS_URI, DBShopConstants.ALLCOLUMNS,null,null,null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        final Shops shops = ShopDAO.getShops(data);
        shopsFragment.setListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(Shop shop, int position) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
            }
        });
        shopsFragment.setShops(shops);
        putMarkerInMap(shops);
    }



    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public void putMarkerInMap(Shops shops){
        for (Shop shop: shops.allShops()) {
            if(shop.getLatitude()>0) {
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(shop.getLatitude(), shop.getLongitude()))
                        .title(shop.getName()));
            }
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
       /* GetOneShopByNameIntereactor shopByNameIntereactor = new GetOneShopByNameIntereactor();
        shopByNameIntereactor.execute(this, new GetOneShopByNameIntereactor.GetOneShopByNameIntereactorCompletion() {
            @Override
            public void completion(Shop shop) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
            }
        }, marker.getTitle());*/



        return true;
    }

    @Override
    public void onMapReady(GoogleMap map) {
//DO WHATEVER YOU WANT WITH GOOGLEMAP

        googleMap = map;
        configMap();
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //googleMap.setMyLocationEnabled(true);
        //map.setTrafficEnabled(true);
        //map.setIndoorEnabled(true);
        //map.setBuildingsEnabled(true);
        //map.getUiSettings().setZoomControlsEnabled(true);
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        // Getting view from the layout file info_window_layout
        View v = getLayoutInflater().inflate(R.layout.callout_info, null);
        final Marker markCallout = marker;
        // Getting the position from the marker
        //final LatLng latLng = marker.getPosition();

        // Getting reference to the TextView to set latitude
        final TextView shopName = (TextView) v.findViewById(R.id.callout_shop_name);

        // Getting reference to the TextView to set longitude
        //TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);



        // Setting the latitude


        // Setting the longitude
        //tvLng.setText("Longitude:"+ latLng.longitude);

        // Returning the view containing InfoWindow contents




        return v;
    }




}



