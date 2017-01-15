package es.elprincipe.madridguide.interactor.activity;


import android.content.Context;

import java.util.List;

import es.elprincipe.madridguide.manager.db.ActivityDAO;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.util.MainThreadUtil;

public class GetActivitiesByNameInteractor {

    private String activityFilterName;

    public interface  GetActivitiesByNameInteractorResponse {
        public void response(Activities activities);
    }

    public GetActivitiesByNameInteractor(String activityFilterName) {
        this.activityFilterName = activityFilterName;
    }

    public void execute(final Context context, final GetActivitiesByNameInteractor.GetActivitiesByNameInteractorResponse completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityDAO dao = new ActivityDAO(context);

                List<Activity> activitiesList = dao.query(activityFilterName);
                final Activities activities = Activities.build(activitiesList);

                MainThreadUtil.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.response(activities);
                    }
                });


            }
        }).start();
    }

}
