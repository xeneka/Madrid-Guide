package es.elprincipe.madridguide.interactor;


import android.content.Context;

import java.util.List;

import es.elprincipe.madridguide.manager.net.NetworkManager;
import es.elprincipe.madridguide.manager.net.ShopEntity;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.model.mapper.shopEntityShopMapper;

public class GetAllShopsInteractor {

    public interface GetAllShopsInteractorResponse{
        public void response(Shops shops);
    }

    public void execute(final Context context, final GetAllShopsInteractorResponse response){

        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getShopsFromServer(new NetworkManager.GetShopListener() {
            @Override
            public void getShopsEntitySuccess(List<ShopEntity> result) {
                List<Shop> shops = new shopEntityShopMapper().map(result);
                if (response != null){
                    response.response(Shops.build(shops));
                }

            }

            @Override
            public void getShopsentitiesDidFail() {
                if (response != null){
                    response.response(null);
                }
            }
        });




    }
}
