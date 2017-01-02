package es.elprincipe.madridguide.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetIsOk {

    public static final boolean ConnectionIsOk(Context context) {
        ConnectivityManager conectivity = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conectivity.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
