package es.elprincipe.madridguide.activities;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.fragments.ShopsFragment;
import es.elprincipe.madridguide.interactor.GetAllShopsFromLocalCacheInteractor;
import es.elprincipe.madridguide.interactor.GetOneShopByNameIntereactor;
import es.elprincipe.madridguide.manager.db.DBConstants;
import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.manager.db.provider.MadridGuideProvider;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.navigator.Navigator;
import es.elprincipe.madridguide.view.OnElementClick;

import static es.elprincipe.madridguide.R.id.map;


public class ShopsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, GoogleMap.OnMarkerClickListener {


    private ShopsFragment shopsFragment;
    private MapFragment mapFragment;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);


        shopsFragment = (ShopsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shops_fragment_shops);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(map);

        //googleMap = mapFragment.getMap();

        //configMap();


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
                //putMarkerInMap(shops);
            }
        });


        //LoaderManager loaderManager = getSupportLoaderManager();
        //loaderManager.initLoader(0,null,this);


    }

    private void configMap() {
       /* googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(MAP_TYPE_HYBRID);
        LatLng latLng = new LatLng(40.4168,-3.7038);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,13.0f));
        googleMap.setOnMarkerClickListener(this);*/
    }

    public Shops getShops() {

        ShopDAO dao = new ShopDAO(this);
        List<Shop> shopList = dao.query();
        Shops shops = Shops.build(shopList);
        return shops;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this, MadridGuideProvider.SHOPS_URI, DBConstants.ALLCOLUMNS,null,null,null);
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
        GetOneShopByNameIntereactor shopByNameIntereactor = new GetOneShopByNameIntereactor();
        shopByNameIntereactor.execute(this, new GetOneShopByNameIntereactor.GetOneShopByNameIntereactorCompletion() {
            @Override
            public void completion(Shop shop) {
                Navigator.navigateFromShopsActivityToShopDetailActivity(ShopsActivity.this, shop);
            }
        }, marker.getTitle());
        return true;
    }

}
