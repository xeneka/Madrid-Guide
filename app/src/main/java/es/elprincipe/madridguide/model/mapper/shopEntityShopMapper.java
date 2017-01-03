package es.elprincipe.madridguide.model.mapper;


import java.util.LinkedList;
import java.util.List;

import es.elprincipe.madridguide.manager.net.ShopEntity;
import es.elprincipe.madridguide.model.Shop;

public class shopEntityShopMapper {

    List<Shop> result =  new LinkedList<>();

    public List<Shop> map(List<ShopEntity> shopEntities){


        for(ShopEntity entity:shopEntities){
            Shop shop = new Shop(entity.getId(), entity.getName());
            shop.setDescription(entity.getDescriptionES());
            shop.setLogoImgUrl(entity.getLogoImg());
            shop.setLatitude(entity.getLatitude());
            shop.setLongitude(entity.getLongitude());
            shop.setImageUrl(entity.getImg());
            shop.setAddress(entity.getAddress());



            result.add(shop);

        }

        return result;

    }

}
