package es.elprincipe.madridguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.util.Constants;

public class ShopDetailActivity extends AppCompatActivity {


    Shop shop;

    @BindView(R.id.activity_shop_detail_name)
    TextView name;

    @BindView(R.id.shop_detail_image)
    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        ButterKnife.bind(this);

        getDetailShopFromCallingIntent();


        updateUI();
    }

    private void getDetailShopFromCallingIntent() {
        Intent i = getIntent();

        if (i != null){
            shop = (Shop) i.getSerializableExtra(Constants.INTENT_KEY_SHOP_DETAIL);
        }
    }

    private void updateUI(){

        name.setText(shop.getName());

        //Picasso.load(shop.getLogoImgUrl()).into(shop.getLogoImgUrl());

        Picasso.with(this).load(shop.getLogoImgUrl()).into(logo);
    }

}
