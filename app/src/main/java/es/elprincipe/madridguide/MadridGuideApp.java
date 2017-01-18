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

        Picasso.with(getApplicationContext()).setLoggingEnabled(false);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(false);

    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }




}
