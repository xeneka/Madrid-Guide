package es.elprincipe.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import es.elprincipe.madridguide.util.PreferenciesApplication;

/**
 * Created by Antonio on 12/12/16.
 */

public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        // Cosas de inicializar apliacation

        appContext = new WeakReference<Context> (getApplicationContext());
        PreferenciesApplication pf = new PreferenciesApplication();


        Picasso.with(getApplicationContext()).setLoggingEnabled(false);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(false);


    }

    

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext(){
        return appContext.get();
    }


}
