package es.elprincipe.madridguide.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class PreferenciesApplication {

    private static final String ULTIMATE_UPDATE = "ULTIMATE_UPDATE" ;

    public final void UpdateDateDownload(Context context){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        SharedPreferences.Editor saveConfig = this.settingApp(context).edit();
        saveConfig.putString(ULTIMATE_UPDATE,df.format(today));
        saveConfig.commit();
    }

    public final boolean updateNow(Context context){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        boolean update = false;

        try{
            date = df.parse(this.settingApp(context).getString(ULTIMATE_UPDATE,"1970-01-01"));
        }catch (Exception e){
            Log.v(getClass().getName(), e.getMessage());
        }

        if (DateUtil.getDateDifToNow(date) > 7){
            update = true;
        }

    return update;

    }



    private SharedPreferences settingApp(Context context){
        SharedPreferences setting = context.getSharedPreferences(ULTIMATE_UPDATE, MODE_PRIVATE);
        return setting;
    }

}
