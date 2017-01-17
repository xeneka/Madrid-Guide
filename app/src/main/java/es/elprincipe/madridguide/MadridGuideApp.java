package es.elprincipe.madridguide;

import android.app.Application;

import com.squareup.picasso.Picasso;

/**
 * Created by Antonio on 12/12/16.
 */

public class MadridGuideApp extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        // Cosas de inicializar apliacation

        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }




}
