package es.elprincipe.madridguide.navigator;

import android.content.Intent;

import es.elprincipe.madridguide.activities.MainActivity;
import es.elprincipe.madridguide.activities.ShopsActivity;

/**
 * Created by Antonio on 12/12/16.
 */
public class Navigator {
    public static Intent navigateFromMainActivityToShopsActivity(final MainActivity mainActivity) {

        final Intent i = new Intent(mainActivity, ShopsActivity.class);

        mainActivity.startActivity(i);

        return i;

    }
}
