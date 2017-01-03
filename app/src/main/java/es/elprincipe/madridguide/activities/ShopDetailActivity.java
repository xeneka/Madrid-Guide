package es.elprincipe.madridguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.fragments.ShopsDetailFragment;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.util.Constants;

public class ShopDetailActivity extends AppCompatActivity {



    Shop shop;
    private ShopsDetailFragment shopDetailFragment;


    /*@BindView(R.id.shop_detail_image)
    ImageView logo;*/

    @BindView(R.id.toolbar_activity_detail)
    Toolbar toolbar;

    @BindView(R.id.toolbar_activity_detail_title) TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);



        shopDetailFragment = (ShopsDetailFragment) getSupportFragmentManager().findFragmentById(R.id.activity_shop_detail_fragment);


        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getDetailShopFromCallingIntent();


        //updateUI();
    }

    private void getDetailShopFromCallingIntent() {
        Intent i = getIntent();

        if (i != null){
            shop = (Shop) i.getSerializableExtra(Constants.INTENT_KEY_SHOP_DETAIL);
            if (shop != null) {
                shopDetailFragment.setShop(shop);
                toolbarTitle.setText(shop.getName());
            }else{
                Log.v(getClass().getName(), "NULOOOO");
            }

        }
    }

    private void updateUI(){



        //Picasso.load(shop.getLogoImgUrl()).into(shop.getLogoImgUrl());

        //Picasso.with(this).load(shop.getLogoImgUrl()).into(logo);
    }

}
