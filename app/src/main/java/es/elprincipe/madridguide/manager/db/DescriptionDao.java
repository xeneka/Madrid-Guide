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

import es.elprincipe.madridguide.model.activity.Description;

import static android.R.attr.id;
import static es.elprincipe.madridguide.manager.db.DBDescriptionConstants.ALLCOLUMNS;
import static es.elprincipe.madridguide.manager.db.DBDescriptionConstants.KEY_DESCRIPTION_DESCRIPTION;
import static es.elprincipe.madridguide.manager.db.DBDescriptionConstants.KEY_DESCRIPTION_ID;
import static es.elprincipe.madridguide.manager.db.DBDescriptionConstants.KEY_DESCRIPTION_ID_ACTIVITY;
import static es.elprincipe.madridguide.manager.db.DBDescriptionConstants.KEY_DESCRIPTION_LANGUAGE;
import static es.elprincipe.madridguide.manager.db.DBDescriptionConstants.TABLE_DESCRIPTION;

public class DescriptionDao implements DAOPersistable<Description> {

    private WeakReference<Context> context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public DescriptionDao(Context context, DBHelper dbHelper){
        this.context = new WeakReference<Context>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();

    }

    public DescriptionDao(Context context) {
        this(context , DBHelper.getInstance(context));
    }

    public static @NonNull
    ContentValues getContentValues(final  @NonNull Description description){

        final ContentValues contentValues = new ContentValues();

        if (description == null){
            return contentValues;
        }

        contentValues.put(KEY_DESCRIPTION_ID_ACTIVITY, description.getActivityName());
        contentValues.put(KEY_DESCRIPTION_LANGUAGE, description.getLanguage());
        contentValues.put(KEY_DESCRIPTION_DESCRIPTION, description.getDescription());

        return contentValues;

    }

    @Override
    public long insert(@NonNull Description description) {

        if (description == null) {
            return 0;
        }
        // insert
        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = dbHelper.getWritableDatabase().insert(TABLE_DESCRIPTION, null, this.getContentValues(description));
            description.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return id;
    }

    @Override
    public void update(long id, @NonNull Description data) {

    }

    @Override
    public int delete(long id) {

        return db.delete(TABLE_DESCRIPTION,  KEY_DESCRIPTION_ID + " = ?" , new String[]{""+id});
    }

    public int deleteAllForActivity(long idActivity){
        return db.delete(TABLE_DESCRIPTION,  KEY_DESCRIPTION_ID_ACTIVITY + " = ?" , new String[]{""+id});
    }

    @Override
    public void deleteAll() {
        dbHelper.getWritableDatabase().delete(TABLE_DESCRIPTION,  null,null);
    }

    @Nullable
    @Override
    public Cursor queryCursor() {

        String where = KEY_DESCRIPTION_ID + "=" + id;
        Cursor c = db.query(TABLE_DESCRIPTION, ALLCOLUMNS, null, null,null, null, KEY_DESCRIPTION_ID);

        if (c!=null && c.getCount() >0){
            c.moveToFirst();
        }

        return c;
    }


    public Cursor queryCursor(String activityName, String language) {

        String where = KEY_DESCRIPTION_ID_ACTIVITY + "= '" + activityName + "' and " + KEY_DESCRIPTION_LANGUAGE + " = '" + language +"'";
        Cursor c = db.query(TABLE_DESCRIPTION, ALLCOLUMNS, where, null,null, null, KEY_DESCRIPTION_ID);

        if (c!=null && c.getCount() >0){
            c.moveToFirst();
        }

        return c;
    }

    @Override
    public Description query(long id) {

        Cursor c = db.query(TABLE_DESCRIPTION, ALLCOLUMNS, KEY_DESCRIPTION_ID + " = " + id, null, null, null, KEY_DESCRIPTION_ID); if (c != null && c.getCount() == 1) { c.moveToFirst(); } else { return null; } Description description = getDescription(c); return description;
    }

    @Nullable

    public List<Description> query(String activityName, String language) {

        Cursor c;

        if (activityName == null){
            c = queryCursor();
        }else{
            c = queryCursor(activityName, language);
        }





        if (c== null || !c.moveToFirst()) {

            return null;
        }

        List<Description> descriptions = new LinkedList<Description>();

        c.moveToFirst();
        do{

            Description description= getDescription(c);
            descriptions.add(description);
        }while (c.moveToNext());



        return descriptions;
    }

    public List<Description> query(){
        return query(null, null);
    }


    @NonNull
    public static Description getDescription(Cursor c) {
        long identifier = c.getLong(c.getColumnIndex(KEY_DESCRIPTION_ID));
        String description_text = c.getString(c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION));
        String identifier_activity = c.getString(c.getColumnIndex(KEY_DESCRIPTION_ID_ACTIVITY));
        String language = c.getString(c.getColumnIndex(KEY_DESCRIPTION_LANGUAGE));

        Description description = new Description(identifier, language,description_text,identifier_activity);

        return description;
    }


}
