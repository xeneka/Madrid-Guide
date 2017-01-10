package es.elprincipe.madridguide;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import es.elprincipe.madridguide.manager.db.DBShopConstants;
import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.manager.db.provider.MadridGuideProvider;
import es.elprincipe.madridguide.model.Shop;

public class MadridGuideProviderTest extends AndroidTestCase {


    public void testQueryAllShops(){
        ContentResolver cr =getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBShopConstants.ALLCOLUMNS,null,null,null);
        assertNotNull(c);
    }

    public void testInsertAsShop(){

        final ContentResolver cr = getContext().getContentResolver();

        final Cursor beforeCursor = cr.query(MadridGuideProvider.SHOPS_URI,DBShopConstants.ALLCOLUMNS,null,null,null);
        final int beforeCount = beforeCursor.getCount();

        Shop shop = new Shop(1,"Little shop of horrors");
        Uri insertedUri = cr.insert(MadridGuideProvider.SHOPS_URI, ShopDAO.getContentValues(shop));
        assertNotNull(insertedUri);

        final Cursor afterCursor = cr.query(MadridGuideProvider.SHOPS_URI,DBShopConstants.ALLCOLUMNS,null,null,null);
        final int afterCount = afterCursor.getCount();

        assertEquals(beforeCount+1, afterCount);


    }

    public void testDeleteShop(){

        final ContentResolver  cr = getContext().getContentResolver();

        Shop shop = new Shop(1, "Shop for error");

        int id = cr.delete(MadridGuideProvider.SHOPS_URI, String.valueOf(shop.getId()),null);

    }

}
