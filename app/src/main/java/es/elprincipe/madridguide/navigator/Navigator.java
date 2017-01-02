package es.elprincipe.madridguide.navigator;

import android.content.Intent;

import es.elprincipe.madridguide.activities.MainActivity;
import es.elprincipe.madridguide.activities.ShopDetailActivity;
import es.elprincipe.madridguide.activities.ShopsActivity;
import es.elprincipe.madridguide.model.Shop;
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
}
