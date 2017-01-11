package es.elprincipe.madridguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.adapter.ActivitiesAdapter;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.view.OnElementClick;

public class ActivitiesFragment extends Fragment {

    private RecyclerView activityRecyclerView;
    private ActivitiesAdapter adapter;
    private Activities activities;

    private OnElementClick<Activity> listener;

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        activityRecyclerView = (RecyclerView) view.findViewById(R.id.activity_recycler_view);
        activityRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void updateUI() {
        adapter = new ActivitiesAdapter(activities, getActivity());
        activityRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<Activity>() {
            @Override
            public void clickedOn(Activity activity, int position) {

                if (listener != null){
                    listener.clickedOn(activity,position);
                }

            }
        });



    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
        updateUI();
    }

    public OnElementClick<Activity> getListener() {
        return listener;
    }

    public void setListener(OnElementClick<Activity> listener) {
        this.listener = listener;
    }


}
