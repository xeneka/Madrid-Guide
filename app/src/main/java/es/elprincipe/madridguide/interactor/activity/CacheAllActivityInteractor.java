package es.elprincipe.madridguide.interactor.activity;


import android.content.Context;
import android.util.Log;

import es.elprincipe.madridguide.manager.db.ActivityDAO;
import es.elprincipe.madridguide.model.activity.Activities;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.util.MainThreadUtil;

public class CacheAllActivityInteractor {





    public interface CacheAllActivityInteractorResponse{
        public void response(boolean success);
    }

    public void execute(final Context context, final Activities activities, final CacheAllActivityInteractorResponse response){


        new Thread(new Runnable() {
            @Override
            public void run() {

                ActivityDAO activityDAO = new ActivityDAO(context);
                for(Activity activity:activities.allActivities()){
                    Log.v(getClass().getName(), activity.getName());
                    if(activityDAO.insert(activity) == 0){
                        break;
                    }

                }

                MainThreadUtil.run(new Runnable() {
                    @Override
                    public void run() {
                        if(response != null){
                            response.response(true);
                        }
                    }
                });


            }
        }).start();

    }

}
