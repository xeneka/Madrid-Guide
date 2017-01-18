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

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.interactor.activity.GetDescriptionByActivityInteractor;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.model.activity.Description;
import es.elprincipe.madridguide.util.UrlFileName;

public class ActivityDetailFragment extends Fragment {

    public static final String ACTIVITY_DETAIL = "ActivityDetailFragment";
    private Activity activity = null;

    @BindView(R.id.fragment_activity_detail_address)
    TextView address;

    @BindView(R.id.fragment_activity_detail_description)
    TextView description;

    @BindView(R.id.fragment_activity_detail_image)
    ImageView activityImage;

    @BindView(R.id.imageMapDetail)
    ImageView imageMapDetail;

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

        String  path = new String(String.valueOf(getContext().getFilesDir()));
        String fileName = new UrlFileName(activity.getImageUrl()).fileName();
        File fileimage = new File(path+"/images/"+fileName);
        File fileImageMap = new File(path+"/images/"+"mapa-"+activity.getName().trim()+".jpg");

        Picasso.with(getContext()).load(fileimage).into(activityImage);

        Picasso.with(getContext()).load(fileImageMap)
                .placeholder(R.drawable.shoppingbag)
                .into(imageMapDetail);

    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        String language = "en";

        if (Locale.getDefault().getDisplayLanguage() == "English"){
            language="es";
        }
        GetDescriptionByActivityInteractor getDescriptionByActivityInteractor = new GetDescriptionByActivityInteractor(activity.getName(),language);
        getDescriptionByActivityInteractor.execute(getContext(), new GetDescriptionByActivityInteractor.GetDescriptionByActivityInteractorResponse() {
            @Override
            public void response(Description description) {
                ActivityDetailFragment.this.description.setText(description.getDescription());
            }
        });
        updateUI();
    }

}
