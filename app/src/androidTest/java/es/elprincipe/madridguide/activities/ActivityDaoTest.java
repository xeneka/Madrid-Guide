package es.elprincipe.madridguide.activities;


import android.database.Cursor;
import android.test.AndroidTestCase;

import es.elprincipe.madridguide.manager.db.ActivityDAO;
import es.elprincipe.madridguide.model.activity.Activity;

public class ActivityDaoTest extends AndroidTestCase {

    public void testCanInsertNewActivity(){

        ActivityDAO sut = new ActivityDAO(getContext());

        int count = getCount(sut);
        long id = insertTestShop(sut);
        assertTrue(id >0);
        assertTrue(count + 1 == sut.queryCursor().getCount() );

    }

    public void testDeleteActivity(){

        ActivityDAO sut = new ActivityDAO(getContext());


        long id = insertTestShop(sut);
        int count = getCount(sut);

        assertEquals(1,sut.delete(id));
        assertTrue(count - 1 == sut.queryCursor().getCount());

    }

    public void testDeleteAll(){
        ActivityDAO sut = new ActivityDAO(getContext());
        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0,count);
    }

    public void testReturnOneActivity(){
        ActivityDAO actitivy = new ActivityDAO(getContext());
        long id = insertTestShop(actitivy);
        Activity sut = actitivy.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(),"Prueba 1");

    }



    // Util methods
    public int getCount(ActivityDAO sut){
        Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

    public long insertTestShop(ActivityDAO sut){
        Activity activity = new Activity(1, "Prueba 1").setAddress("Dir pruba");
        return sut.insert(activity);
    }

}
