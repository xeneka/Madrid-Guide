package es.elprincipe.madridguide.interactor.shop;


import android.content.Context;

import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.util.MainThreadUtil;

public class GetOneShopByNameIntereactor {

    public interface GetOneShopByNameIntereactorCompletion{
        public void completion(Shop shop);
    }

    public void execute(final Context context, final GetOneShopByNameIntereactorCompletion completion, final String nameShop){

        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);
                final Shop shop = dao.getOneShop(nameShop);

                MainThreadUtil.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shop);
                    }
                });
                
            }
        }).start();

    }

}
