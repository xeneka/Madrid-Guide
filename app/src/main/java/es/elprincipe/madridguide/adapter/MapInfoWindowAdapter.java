package es.elprincipe.madridguide.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Hashtable;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.activities.ActivitiesActivity;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.navigator.Navigator;
import es.elprincipe.madridguide.util.UrlFileName;


public class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {
        private final Hashtable<String, Boolean> markerSet;
        private Context context;
        private View popup;
        private Activities activities;
        private GoogleMap map;

        public MapInfoWindowAdapter(Context context, Hashtable<String, Boolean> markerSet, Activities activities, GoogleMap map) {
            this.context = context;
            this.markerSet = markerSet;
            this.activities = activities;
            this.map = map;

        }





        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }



        @Override
        public View getInfoContents(Marker marker) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            popup = inflater.inflate(R.layout.callout_info, null);

            map.setOnInfoWindowClickListener(this);



            ImageView imageView = (ImageView) popup.findViewById(R.id.logoActivity);
            TextView nameActivity = (TextView) popup.findViewById(R.id.callout_shop_name);

            Activity activity = getActivityWithMarker(marker);
            nameActivity.setText(activity.getName());
            String  path = new String(String.valueOf(context.getFilesDir()));
            final String fileName = new UrlFileName(activity.getLogoImgUrl()).fileName();
            final File fileimage = new File(path+"/images/"+fileName);



            boolean isImageLoaded = markerSet.get(marker.getId());
            if (isImageLoaded) {
                Picasso.with(context)
                        .load(fileimage)
                        .placeholder(R.drawable.download)
                        .into(imageView);

            } else {
                isImageLoaded = true;
                markerSet.put(marker.getId(), isImageLoaded);
                Picasso.with(context)
                        .load(fileimage)
                        .placeholder(R.drawable.download)
                        .into(imageView, new InfoWindowRefresher(marker));


            }

            return popup;
        }

    private Activity getActivityWithMarker(Marker marker) {
        int  idactivity = Integer.parseInt(marker.getSnippet());
        return activities.get(idactivity);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        
        Navigator.navigateFromActivitiesActivityToActivityDetail((ActivitiesActivity) context, getActivityWithMarker(marker));

    }


    public class InfoWindowRefresher implements Callback {
        private Marker markerToRefresh;

        public InfoWindowRefresher(Marker markerToRefresh) {
            this.markerToRefresh = markerToRefresh;
        }

        @Override
        public void onSuccess() {
            markerToRefresh.showInfoWindow();
        }

        @Override
        public void onError() {}
    }

    }


