package es.elprincipe.madridguide.navigator;

import android.content.Intent;

import es.elprincipe.madridguide.activities.ActivitiesActivity;
import es.elprincipe.madridguide.activities.ActivityDetail;
import es.elprincipe.madridguide.activities.MainActivity;
import es.elprincipe.madridguide.activities.ShopDetailActivity;
import es.elprincipe.madridguide.activities.ShopsActivity;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.util.Constants;

/**
 * Created by Antonio on 12/12/16.
 */
public class Navigator {
    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {

        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;

    }

    public static  Intent navigateFromShopsActivityToShopDetailActivity(final ShopsActivity shopsActivity, Shop detail){
        final Intent i = new Intent(shopsActivity, ShopDetailActivity.class);

        i.putExtra(Constants.INTENT_KEY_SHOP_DETAIL, detail);
        shopsActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromMainActivitytoActivitiesActivity(final MainActivity mainActivity){

        final Intent i = new Intent(mainActivity, ActivitiesActivity.class);
        mainActivity.startActivity(i);
        return i;
    }

    public static Intent navigateFromActivitiesActivityToActivityDetail(final ActivitiesActivity activitiesActivity, Activity detail){

        final Intent i = new Intent(activitiesActivity, ActivityDetail.class);
        i.putExtra(Constants.INTENT_KEY_ACTIVITY_DETAIL, detail);
        activitiesActivity.startActivity(i);
        return i;
    }

}
