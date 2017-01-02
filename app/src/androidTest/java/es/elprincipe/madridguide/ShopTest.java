package es.elprincipe.madridguide;

import android.test.AndroidTestCase;

import es.elprincipe.madridguide.model.Shop;


public class ShopTest extends AndroidTestCase {


    public static final String SHOP = "shop";
    public static final String ADDRESS = "ADDRESS";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String URL = "URL";

    public void testCanCreateAShop(){
        Shop sut = new Shop(0, SHOP);
        assertNotNull(sut);
    }

    public void testANewShopStoreDataCorrectly(){
        Shop sut = new Shop(10, SHOP);
        assertEquals(10, sut.getId());
        assertEquals(SHOP, sut.getName());
    }

    public void testANewShopStoreDataInPorpertyCorrectly(){

        Shop sut = new Shop(11, SHOP)
                .setAddress(ADDRESS)
                .setDescription(DESCRIPTION)
                .setImageUrl(URL);

        assertEquals(sut.getAddress(), ADDRESS);
        assertEquals(sut.getDescription(), DESCRIPTION);
        assertEquals(sut.getImageUrl(),URL);


    }


}
