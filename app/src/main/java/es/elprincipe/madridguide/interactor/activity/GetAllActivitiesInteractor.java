package es.elprincipe.madridguide.interactor.activity;


import android.content.Context;

import java.util.List;

import es.elprincipe.madridguide.manager.net.ActivityEntity;
import es.elprincipe.madridguide.manager.net.NetworkManagerforActivity;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.model.mapper.ActivityEntityMapper;

public class GetAllActivitiesInteractor {

    public interface GetAllActivitiesInteractorResponse{
        public void response(Activities activities);
    }

    public void execute(final Context context, final GetAllActivitiesInteractorResponse response){

        NetworkManagerforActivity networkManager = new NetworkManagerforActivity(context);
        networkManager.getActivitiesFromServer(new NetworkManagerforActivity.GetActivityListener() {
            @Override
            public void getActivitiesEntitySuccess(List<ActivityEntity> result) {
                List<Activity> activities = new ActivityEntityMapper().map(result);
                if (response != null){
                    response.response(Activities.build(activities));
                }
            }

            @Override
            public void getActivitiesentitiesDidFail() {
                if (response != null){
                    response.response(null);
                }
            }

        });

    }
}
