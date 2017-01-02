package es.elprincipe.madridguide.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.view.OnElementClick;
import es.elprincipe.madridguide.view.ShopRowViewHolder;

public class ShopsAdapter extends RecyclerView.Adapter<ShopRowViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Shops shops;



    private OnElementClick<Shop> listener;


    public ShopsAdapter(Shops shops, Context context) {
        this.shops = shops;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ShopRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.row_shop,parent,false);

        return new ShopRowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopRowViewHolder row, final int position) {

        final Shop shop = shops.get(position);
        row.setShop(shop);

        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listener != null){
                    listener.clickedOn(shop, position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return (int) shops.size();
    }

    public void setOnElementClickListener(@NonNull final OnElementClick listener){
        this.listener = listener;
    }
}
