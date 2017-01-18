package es.elprincipe.madridguide.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
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
import es.elprincipe.madridguide.navigator.Navigator;
import es.elprincipe.madridguide.util.InternetIsOk;
import es.elprincipe.madridguide.util.NameMap;
import es.elprincipe.madridguide.util.PreferenciesApplication;
import es.elprincipe.madridguide.util.UrlFileName;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static WeakReference<Context> appContext;

    @BindView(R.id.activity_main_shops_button)
    ImageButton shopsButton;
    @BindView(R.id.activity_main_activity_button)
    ImageButton activityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupShopsButton();
        appContext = new WeakReference<Context>(getApplicationContext());
        loadDataAndImageWhenIsNecessary();
    }




    private void loadDataAndImageWhenIsNecessary() {
        PreferenciesApplication pf = new PreferenciesApplication();

        if(pf.updateNow(this) && !InternetIsOk.ConnectionIsOk(this)){
            Toast toast = Toast.makeText(this, R.string.imposiblereloaddata,Toast.LENGTH_LONG);
            toast.show();
        }


        if (pf.updateNow(this) && InternetIsOk.ConnectionIsOk(this)) {
            clearImageFiles();
            clearDataBaseShop();
            clearDataBaseActivities();
            messageLoadData();

            updateShops();
            updateAcitivities();
            pf.UpdateDateDownload(this);
        } else{
            shopsButton.setEnabled(true);
            activityButton.setEnabled(true);
        }
    }

    private void messageLoadData() {
        Toast toast = Toast.makeText(this, R.string.updatedata,Toast.LENGTH_LONG);
        toast.show();
    }

    private void setupShopsButton() {

        shopsButton.setOnClickListener(this);
        activityButton.setOnClickListener(this);
        shopsButton.setEnabled(false);
        activityButton.setEnabled(false);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.activity_main_activity_button:
                Navigator.navigateFromMainActivitytoActivitiesActivity(MainActivity.this);
                break;
            case R.id.activity_main_shops_button:
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
                break;
            default:
                break;
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
                activityButton.setImageResource(R.drawable.activities);
            }
        });
    }

    private void updateAcitivities() {
        activityButton.setImageResource(R.drawable.activities_black);
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
                            ImageData imageLogo = new ImageData(activity.getLogoImgUrl(),new UrlFileName(activity.getLogoImgUrl()).fileName() );
                            imageList.add(imageLogo);
                            String urlImageMap = "http://maps.googleapis.com/maps/api/staticmap?center="+String.valueOf(activity.getLatitude())+","+String.valueOf(activity.getLongitude())+"&zoom=17&size=320x220&scale=2&markers=%7Ccolor:0x9C7B14%7C40.452048,-3.686463&key=AIzaSyBa4vb5F5DiIvNAfJ6p2OVy46KRe1pNjxk";
                            ImageData imageMap = new ImageData(urlImageMap, NameMap.NameMap(activity.getName()));
                            imageList.add(imageMap);
                        }
                        downloadImagesActivities(imageList);
                        activityButton.setEnabled(true);

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
                        shopsButton.setEnabled(true);
                    }
                });
            }
        });
    }

    public static Context getAppContext(){
        return appContext.get();
    }


}
