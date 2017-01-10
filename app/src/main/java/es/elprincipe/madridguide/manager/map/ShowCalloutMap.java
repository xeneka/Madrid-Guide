package es.elprincipe.madridguide.manager.map;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.lang.ref.WeakReference;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.interactor.shop.GetOneShopByNameIntereactor;
import es.elprincipe.madridguide.model.Shop;

public class ShowCalloutMap implements GoogleMap.InfoWindowAdapter {

    private View view;
    LayoutInflater inflater = null;
    private Marker currentlyClickedMarker;
    private Shop shop;
    private WeakReference<Context> context;



    public void ShowCalloutMap(final Context context, final LayoutInflater inflater) {
        //Inflating the InfoWindow view.
        this.inflater = inflater;
        view = inflater.inflate(R.layout.callout_info, null);
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public View getInfoWindow(final Marker marker) {
        currentlyClickedMarker = marker;
        TextView shopName = (TextView) view.findViewById(R.id.callout_shop_name);

        GetOneShopByNameIntereactor shopByNameIntereactor = new GetOneShopByNameIntereactor();
        shopByNameIntereactor.execute(context.get(), new GetOneShopByNameIntereactor.GetOneShopByNameIntereactorCompletion() {
            @Override
            public void completion(Shop shop) {
                getInfoContents(marker);
                Log.v(getClass().getName(), shop.getName());

            }
        }, marker.getTitle());

        return null;

    }

    @Override
    public View getInfoContents(Marker marker) {
        if ( currentlyClickedMarker != null && currentlyClickedMarker.isInfoWindowShown() ) {
            currentlyClickedMarker.hideInfoWindow();
            currentlyClickedMarker.showInfoWindow();

        }
        return null;
    }
}
