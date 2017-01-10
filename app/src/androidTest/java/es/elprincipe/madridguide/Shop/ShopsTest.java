package es.elprincipe.madridguide.shop;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;


public class ShopsTest extends AndroidTestCase {


    public void testCreatingAShopsWithNullListReturnNonNullsShops(){
        Shops sut = Shops.build(null);

        assertNotNull(sut);
        assertNotNull(sut.allShops());
    }

    public void testCreatingAShopsWithAListReturnNonNullsShops(){

        List<Shop> data = getShops();

        Shops sut = Shops.build(data);

        assertNotNull(sut);
        assertNotNull(sut.allShops());
        assertEquals(sut.allShops(),data);
        assertEquals(sut.allShops().size(), data.size());
        assertEquals(sut.get(0).getAddress(),data.get(0).getAddress());
    }

    @NonNull
    private List<Shop> getShops() {
        List<Shop> data = new ArrayList<>();
        data.add(new Shop(1,"1").setAddress("ADDRESS 1"));
        data.add(new Shop(2,"2").setAddress("ADDRESS 2"));
        return data;
    }


}
