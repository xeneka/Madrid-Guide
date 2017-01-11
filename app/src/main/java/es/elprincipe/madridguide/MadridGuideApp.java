package es.elprincipe.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import es.elprincipe.madridguide.interactor.activity.CacheAllActivityInteractor;
import es.elprincipe.madridguide.interactor.activity.GetAllActivitiesInteractor;
import es.elprincipe.madridguide.interactor.shop.CacheAllShopInteractor;
import es.elprincipe.madridguide.interactor.shop.GetAllShopsInteractor;
import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.util.InternetIsOk;
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


        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);

        if (pf.updateNow(this) && InternetIsOk.ConnectionIsOk(this)) {

                clearDataBase();
                updateShops();
                updateAcitivities();
        }
    }

    private void clearDataBase() {
        ShopDAO dao = new ShopDAO(getApplicationContext());
        dao.deleteAll();
    }

    private void updateAcitivities() {
        new GetAllActivitiesInteractor().execute(getApplicationContext(), new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
            @Override
            public void response(Activities activities) {

                new CacheAllActivityInteractor().execute(getAppContext(), activities, new CacheAllActivityInteractor.CacheAllActivityInteractorResponse() {
                    @Override
                    public void response(boolean success) {

                    }
                });
            }
        });
    }

    private void updateShops() {
        new GetAllShopsInteractor().execute(getApplicationContext(), new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
            @Override
            public void response(Shops shops) {

                new CacheAllShopInteractor().execute(getApplicationContext(), shops , new CacheAllShopInteractor.CacheAllShopsInteractorResponse(){
                    @Override
                    public void response(boolean success){

                    }
                });
            }
        });
    }







    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext(){
        return appContext.get();
    }


}
