package es.elprincipe.madridguide;

import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.model.Shop;


public class ShopDAOTests extends AndroidTestCase {

    public void testCanInsertANewShop(){

        ShopDAO sut = new ShopDAO(getContext());

        int count = getCount(sut);

        long id = insertTestShop(sut);
        assertTrue(id >0);
        assertTrue(count + 1 == sut.queryCursor().getCount() );

    }

    public void testCanDeleteAShop(){

        ShopDAO sut = new ShopDAO(getContext());

        long id = insertTestShop(sut);

        int count = getCount(sut);

        assertEquals(1,sut.delete(id));


        assertTrue(count - 1 == sut.queryCursor().getCount() );


    }

    public void testDeleteAll(){
        ShopDAO sut = new ShopDAO(getContext());
        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0,count);

    }

    public void testQueryOneShop(){
        ShopDAO dao = new ShopDAO(getContext());
        long id = insertTestShop(dao);
        Shop sut = dao.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(),"1");
    }

    public void testQueryAllShop(){
        ShopDAO dao = new ShopDAO(getContext());
        long id = insertTestShop(dao);

        List<Shop> shopList = dao.query();
        assertNotNull(shopList);
        assertTrue(shopList.size() >0);


    }

    private long insertTestShop(ShopDAO sut) {
        Shop shop = new Shop(1, "1").setAddress("ADDRESS 1");
        return sut.insert(shop);
    }

    private int getCount(ShopDAO sut) {
        Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }


}
