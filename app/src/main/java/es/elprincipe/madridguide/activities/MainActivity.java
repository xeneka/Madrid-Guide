package es.elprincipe.madridguide.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.navigator.Navigator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.activity_main_shops_button)
    ImageButton shopsButton;
    @BindView(R.id.activity_main_activity_button)
    ImageButton activityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupShopsButton();


    }

    private void setupShopsButton() {

        shopsButton.setOnClickListener(this);
        activityButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.activity_main_activity_button:
                Navigator.navigateFromMainActivitytoActivitiesActivity(MainActivity.this);
                break;
            case R.id.activity_main_shops_button:
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
                break;
            default:
                break;
        }
    }
}
