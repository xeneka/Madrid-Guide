package es.elprincipe.madridguide.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.model.Shop;


public class ShopRowViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    ImageView logoImageView;
    private WeakReference<Context> context;

    public ShopRowViewHolder(View rowShop){
        super(rowShop);

        nameTextView = (TextView) rowShop.findViewById(R.id.row_shop_name);
        logoImageView = (ImageView) rowShop.findViewById(R.id.row_shop_logo);
        context = new WeakReference<Context>(rowShop.getContext());
    }

    public void setShop(final @NonNull  Shop shop){

        if(shop == null){
            return;
        }

        nameTextView.setText(shop.getName());
        Picasso.with(context.get())
                .load(shop.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);

    }

}
