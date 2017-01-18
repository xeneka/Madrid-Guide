package es.elprincipe.madridguide.Description;


import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.LinkedList;
import java.util.List;

import es.elprincipe.madridguide.manager.db.DescriptionDao;
import es.elprincipe.madridguide.model.activity.Description;

public class DescripctionDaoTest extends AndroidTestCase {

    public static final String DESCRIPCION_DE_PRUEBA = "descripcion de prueba";
    public static final String ACTIVITY_NAME = "museo";
    public static final String LANGUAGE = "es";

    public void testCanInsertNewDescription(){

        DescriptionDao sut = new DescriptionDao(getContext());

        int count = getCount(sut);
        long id = insertTestDescription(sut);
        assertTrue(id >0);
        assertTrue(count + 1 == sut.queryCursor().getCount() );

    }

    public void testDeleteDescription(){

        DescriptionDao sut = new DescriptionDao(getContext());


        long id = insertTestDescription(sut);
        int count = getCount(sut);

        assertEquals(1,sut.delete(id));
        assertTrue(count - 1 == sut.queryCursor().getCount());

    }

    public void testDeleteAll(){
        DescriptionDao sut = new DescriptionDao(getContext());
        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0,count);
    }

    public void testReturnOneDescription(){
        DescriptionDao description = new DescriptionDao(getContext());
        long id = insertTestDescription(description);
        Description sut = description.query(id);
        assertNotNull(sut);
        assertEquals(sut.getActivityName(),ACTIVITY_NAME);
        assertEquals(sut.getLanguage(),LANGUAGE);
        assertEquals(sut.getDescription(),DESCRIPCION_DE_PRUEBA);

    }

    public void testReturnOneDescriptionByActivityNameAndLanguage(){
        DescriptionDao description = new DescriptionDao(getContext());
        long id = insertTestDescription(description);
        List<Description> sut= new LinkedList<>();
        sut = description.query(ACTIVITY_NAME,LANGUAGE);
        assertNotNull(sut);
        assertEquals(sut.get(0).getActivityName(),ACTIVITY_NAME);
        assertEquals(sut.get(0).getLanguage(),LANGUAGE);
        assertEquals(sut.get(0).getDescription(),DESCRIPCION_DE_PRUEBA);
    }



    // Util methods
    public int getCount(DescriptionDao sut){
        Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

    public long insertTestDescription(DescriptionDao sut){
        Description description = new Description(1, LANGUAGE, DESCRIPCION_DE_PRUEBA, ACTIVITY_NAME);
        return sut.insert(description);
    }


}
