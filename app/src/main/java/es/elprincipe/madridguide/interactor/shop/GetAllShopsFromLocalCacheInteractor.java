package es.elprincipe.madridguide.interactor.shop;

import android.content.Context;

import java.util.List;

import es.elprincipe.madridguide.manager.db.ShopDAO;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.util.MainThreadUtil;

public class GetAllShopsFromLocalCacheInteractor {

    public interface  OnGetAllShopsFromLocalCacheInteractorCompletion {
        public void completion(Shops shops);
    }

    public void execute(final Context context, final OnGetAllShopsFromLocalCacheInteractorCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);

                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                MainThreadUtil.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });


            }
        }).start();
    }
}



