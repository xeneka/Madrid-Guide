package es.elprincipe.madridguide.interactor.shop;


import android.content.Context;

import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.util.MainThreadUtil;

public class CacheAllShopInteractor {

    public interface CacheAllShopsInteractorResponse {
        public void response(boolean success);
    }

    public void execute(final Context context, final Shops shops, final CacheAllShopsInteractorResponse response){


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ShopDAO dao = new ShopDAO(context);

                        boolean success = true;
                        for(Shop shop: shops.allShops()){
                            success = dao.insert(shop) >0;
                            if(!success){
                                break;
                            }
                        }

                        MainThreadUtil.run(new Runnable() {
                            @Override
                            public void run() {
                                if(response != null){
                                    response.response(true);
                                }
                            }
                        });

                    }
                }).start();





    }

}
