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
import es.elprincipe.madridguide.model.activity.Activity;

public class ActivityDetailFragment extends Fragment {

    public static final String ACTIVITY_DETAIL = "ActivityDetailFragment";
    private Activity activity = null;

    @BindView(R.id.fragment_activity_detail_address)
    TextView address;

    @BindView(R.id.fragment_activity_detail_description)
    TextView description;

    @BindView(R.id.fragment_activity_detail_image)
    ImageView activityImage;

    public ActivityDetailFragment() {}



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_activity_detail,container,false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void updateUI() {
        description.setText("IMPLEMENTAR DESCRIPTION ACTIVITYDETAILFRAGMENT");
        address.setText(activity.getAddress());

        Picasso.with(getContext()).load(activity.getImageUrl()).into(activityImage);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        updateUI();
    }

}
