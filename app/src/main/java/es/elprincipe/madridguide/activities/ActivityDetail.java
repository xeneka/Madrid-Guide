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
import es.elprincipe.madridguide.fragments.ActivityDetailFragment;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.util.Constants;

public class ActivityDetail extends AppCompatActivity {

    Activity activity;
    private ActivityDetailFragment activityDetailFragment;


    @BindView(R.id.toolbar_activity_detail)
    Toolbar toolbar;

    @BindView(R.id.toolbar_activity_detail_title)
    TextView toolbarTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);

        activityDetailFragment = (ActivityDetailFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activities_detail_fragment);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getDetailShopFromCallingIntent();


    }

    private void getDetailShopFromCallingIntent() {
        Intent i = getIntent();

        if (i != null){
            activity = (Activity) i.getSerializableExtra(Constants.INTENT_KEY_ACTIVITY_DETAIL);
            if (activity != null) {
                activityDetailFragment.setActivity(activity);
                toolbarTitle.setText(activity.getName());
            }else{
                Log.v(getClass().getName(), "NULOOOO");
            }

        }
    }



}
