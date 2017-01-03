package es.elprincipe.madridguide.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.model.Shop;

public class ShopsDetailFragment extends Fragment {

    public static final String SHOP_DETAIL = "ShopsDetailFragment";
    private Shop shop = null;

    @BindView(R.id.fragment_shop_detail_address)
    TextView address;

    @BindView(R.id.fragment_shop_detail_description)
    TextView description;

    @BindView(R.id.fragment_shop_detail_image)
    ImageView shopImage;

    public ShopsDetailFragment() {}



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_shop_detail,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void updateUI() {
        description.setText(shop.getDescription());
        address.setText(shop.getAddress());

        Picasso.with(getContext()).load(shop.getImageUrl()).into(shopImage);
    }

    public void setShop(Shop shop) {
        this.shop = shop;
        updateUI();
    }
}

