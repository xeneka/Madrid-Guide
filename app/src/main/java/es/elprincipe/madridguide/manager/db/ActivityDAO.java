package es.elprincipe.madridguide.manager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import es.elprincipe.madridguide.model.activity.Activity;

import static android.R.attr.id;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.ALLCOLUMNS;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_ADDRESS;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_BOOKING_DATA;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_BOOKING_OPERATION;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_EMAIL;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_ID;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_IMAGE_URL;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_LATITUDE;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_LOGO_IMAGE_URL;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_LONGITUDE;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_NAME;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_TELEPHONE;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.KEY_ACTIVITY_URL;
import static es.elprincipe.madridguide.manager.db.DBActivityContants.TABLE_ACTIVITY;

public class ActivityDAO implements DAOPersistable<Activity> {

    private WeakReference<Context> context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;



    public ActivityDAO(Context context, DBHelper dbHelper){
        this.context = new WeakReference<Context>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();

    }

    public ActivityDAO(Context context) {
        this(context , DBHelper.getInstance(context));
    }

    public static @NonNull
    ContentValues getContentValues(final  @NonNull  Activity activity){

        final ContentValues contentValues = new ContentValues();

        if (activity == null){
            return contentValues;
        }

        contentValues.put(KEY_ACTIVITY_ADDRESS, activity.getAddress());
        contentValues.put(KEY_ACTIVITY_TELEPHONE, activity.getTelephone());
        contentValues.put(KEY_ACTIVITY_EMAIL, activity.getEmail());
        contentValues.put(KEY_ACTIVITY_IMAGE_URL,activity.getImageUrl());
        contentValues.put(KEY_ACTIVITY_LOGO_IMAGE_URL,activity.getLogoImgUrl());
        contentValues.put(KEY_ACTIVITY_LATITUDE,activity.getLatitude());
        contentValues.put(KEY_ACTIVITY_LONGITUDE, activity.getLongitude());
        contentValues.put(KEY_ACTIVITY_NAME, activity.getName());
        contentValues.put(KEY_ACTIVITY_URL, activity.getUrl());
        contentValues.put(KEY_ACTIVITY_BOOKING_DATA, activity.getBooking_data());
        contentValues.put(KEY_ACTIVITY_BOOKING_OPERATION, activity.getBooking_operation());

        return contentValues;

    }


    @Override
    public long insert(@NonNull Activity activity) {
        if (activity == null) {
            return 0;
        }
        // insert
        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = dbHelper.getWritableDatabase().insert(DBActivityContants.TABLE_ACTIVITY, null, this.getContentValues(activity));
            activity.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return id;
    }

    @Override
    public void update(long id, @NonNull Activity data) {

    }

    @Override
    public int delete(long id) {
        return db.delete(TABLE_ACTIVITY,  KEY_ACTIVITY_ID + " = ?" , new String[]{""+id});
    }

    @Override
    public void deleteAll() {
        dbHelper.getWritableDatabase().delete(TABLE_ACTIVITY,  null,null);
    }

    @Nullable
    @Override
    public Cursor queryCursor() {

        String where = KEY_ACTIVITY_ID + "=" + id;
        Cursor c = db.query(TABLE_ACTIVITY, ALLCOLUMNS, null, null,null, null, KEY_ACTIVITY_ID);

        if (c!=null && c.getCount() >0){
            c.moveToFirst();
        }

        return c;
    }

    public Cursor queryCursor(String activityName) {

        String where = KEY_ACTIVITY_NAME + " like '%" + activityName +"%'";
        Cursor c = db.query(TABLE_ACTIVITY, ALLCOLUMNS, where, null,null, null, KEY_ACTIVITY_ID);

        if (c!=null && c.getCount() >0){
            c.moveToFirst();
        }

        return c;
    }

    @Override
    public Activity query(long id) {

        Cursor c = db.query(TABLE_ACTIVITY, ALLCOLUMNS, KEY_ACTIVITY_ID + " = " + id, null, null, null, KEY_ACTIVITY_ID); if (c != null && c.getCount() == 1) { c.moveToFirst(); } else { return null; } Activity activity = getActivity(c); return activity;
    }
    
  

    @NonNull
    public static Activity getActivity(Cursor c) {
        long identifier = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ID));
        String name = c.getString(c.getColumnIndex(KEY_ACTIVITY_NAME));

        Activity activity = new Activity(identifier, name);

        activity.setAddress(c.getString(c.getColumnIndex(KEY_ACTIVITY_ADDRESS)));
        activity.setEmail(c.getString(c.getColumnIndex(KEY_ACTIVITY_EMAIL)));
        activity.setImageUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_IMAGE_URL)));
        activity.setLogoImgUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_LOGO_IMAGE_URL)));
        activity.setLatitude(c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LATITUDE)));
        activity.setLongitude(c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LONGITUDE)));
        activity.setUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_URL)));
        activity.setTelephone(c.getString(c.getColumnIndex(KEY_ACTIVITY_TELEPHONE)));
        activity.setBooking_data(c.getString(c.getColumnIndex(KEY_ACTIVITY_BOOKING_DATA)));
        activity.setBooking_operation(c.getString(c.getColumnIndex(KEY_ACTIVITY_BOOKING_OPERATION)));


        return activity;
    }



    @Nullable

    public List<Activity> query(String nameActivity) {

        Cursor c;
        
        if (nameActivity == null){
            c = queryCursor();  
        } else{
            c = queryCursor(nameActivity);
        }
        
        

        // left golden path

        if (c== null || !c.moveToFirst()) {

            return null;
        }

        List<Activity> activities = new LinkedList<Activity>();

        c.moveToFirst();
        do{

            Activity activity = getActivity(c);
            activities.add(activity);
        }while (c.moveToNext());



        return activities;
    }




    public List<Activity> query(){
        return query(null);
    }
    

    public Cursor queryCursor(long id) {

        Cursor c = db.query(TABLE_ACTIVITY, ALLCOLUMNS, "ID = " + id, null,null, null, KEY_ACTIVITY_ID);

        if (c!=null && c.getCount() >0){
            c.moveToFirst();
        }

        return c;

    }




}
