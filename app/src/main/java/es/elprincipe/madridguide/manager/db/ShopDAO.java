package es.elprincipe.madridguide.manager.db;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;

import es.elprincipe.madridguide.model.Shop;

public class ShopDAO implements DAOPersistable<Shop> {

    private WeakReference<Context> context;

    @Override
    public long insert(@NonNull Shop shop) {
        if (shop == null) {
            return 0;
        }
        // insert
        DBHelper dbHelper = DBHelper.getInstance(context.get());
        SQLiteDatabase db = dbHelper.getDB();

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = dbHelper.getWritableDatabase().insert(TABLE_NOTEBOOK, null, this.getContentValues(notebook));
            shop.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        dbHelper.close();
        dbHelper=null;

        return id;
    }

    @Override
    public void update(long id, @NonNull Shop data) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        return null;
    }

    @Override
    public Shop query(long id) {
        return null;
    }

    @Nullable
    @Override
    public List<Shop> query() {
        return null;
    }
}
