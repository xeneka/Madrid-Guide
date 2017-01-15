package es.elprincipe.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import es.elprincipe.madridguide.interactor.activity.CacheAllActivityInteractor;
import es.elprincipe.madridguide.interactor.activity.GetAllActivitiesInteractor;
import es.elprincipe.madridguide.interactor.shop.CacheAllShopInteractor;
import es.elprincipe.madridguide.interactor.shop.GetAllShopsInteractor;
import es.elprincipe.madridguide.manager.db.ActivityDAO;
import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.manager.image.DeleteImageFromInternalStorage;
import es.elprincipe.madridguide.manager.image.DownloadImage;
import es.elprincipe.madridguide.manager.image.ImageData;
import es.elprincipe.madridguide.manager.image.ImageDataList;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.util.InternetIsOk;
import es.elprincipe.madridguide.util.PreferenciesApplication;
import es.elprincipe.madridguide.util.UrlFileName;

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

        if (pf.updateNow(this) && InternetIsOk.ConnectionIsOk(this) || true) {
            clearImageFiles();
            clearDataBaseShop();
            clearDataBaseActivities();

            updateShops();
            updateAcitivities();
        }
    }

    private void clearDataBaseShop() {
        ShopDAO dao = new ShopDAO(getApplicationContext());
        dao.deleteAll();
    }

    private void clearDataBaseActivities(){
        ActivityDAO dao = new ActivityDAO(getApplicationContext());
        dao.deleteAll();
    }

    private void clearImageFiles(){

        DeleteImageFromInternalStorage borrar = new DeleteImageFromInternalStorage(getApplicationContext(), new DeleteImageFromInternalStorage.DeleteImageFromInternalStorageResponse() {
            @Override
            public void completion(boolean success) {

            }
        });

    }

    private void downloadImagesActivities(ImageDataList urlImage){


        DownloadImage images = new DownloadImage(urlImage,this, new DownloadImage.DownloadImageResponse() {
            @Override
            public void response(boolean success) {

            }
        });
    }

    private void updateAcitivities() {
        new GetAllActivitiesInteractor().execute(getApplicationContext(), new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
            @Override
            public void response(final Activities activities) {

                new CacheAllActivityInteractor().execute(getAppContext(), activities, new CacheAllActivityInteractor.CacheAllActivityInteractorResponse() {
                    @Override
                    public void response(boolean success) {

                        // Descargo todas las images de las actividades incluidos mapas


                        ImageDataList imageList = ImageDataList.build();
                        for(Activity activity: activities.allActivities()){

                            ImageData image = new ImageData(activity.getImageUrl(),new UrlFileName(activity.getImageUrl()).fileName() );
                            imageList.add(image);
                            String urlImageMap = "http://maps.googleapis.com/maps/api/staticmap?center="+String.valueOf(activity.getLatitude())+","+String.valueOf(activity.getLongitude())+"&zoom=17&size=320x220&scale=2&markers=%7Ccolor:0x9C7B14%7C40.452048,-3.686463&key=AIzaSyBa4vb5F5DiIvNAfJ6p2OVy46KRe1pNjxk";
                            ImageData imageMap = new ImageData(urlImageMap,"mapa-"+activity.getName().trim()+".jpg");
                            imageList.add(imageMap);
                        }
                        downloadImagesActivities(imageList);



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
