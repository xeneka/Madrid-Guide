package es.elprincipe.madridguide.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 12/12/16.
 */

public class Shops implements IShopsIteratable, IShopsUdatable {


    List<Shop> shops;

    public static @NonNull Shops build(@NonNull  List<Shop> shopList){

        Shops shops = new Shops(shopList);

        if(shopList == null){
            shops.shops = new ArrayList<>();
        }

        return shops;
    }

    private static Shops build(){
        return build(new ArrayList<Shop>());
    }

    private Shops(List<Shop> shops){
        this.shops = shops;
    }


    @Override
    public long size() {
        return shops.size();
    }

    @Override
    public Shop get(long index) {
        return shops.get((int) index);
    }

    @Override
    public List<Shop> allShops() {
        return shops;
    }

    @Override
    public void add(Shop shop) {

        shops.add(shop);

    }

    @Override
    public void delete(Shop shop) {

        shops.remove(shop);
    }

    @Override
    public void edit(Shop newShop, long index) {

         shops.set((int) index , newShop);
    }
}
