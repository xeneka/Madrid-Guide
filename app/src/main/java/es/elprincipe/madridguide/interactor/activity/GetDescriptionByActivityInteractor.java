package es.elprincipe.madridguide.interactor.activity;


import android.content.Context;

import java.util.List;

import es.elprincipe.madridguide.manager.db.DescriptionDao;
import es.elprincipe.madridguide.model.activity.Description;
import es.elprincipe.madridguide.util.MainThreadUtil;



public class GetDescriptionByActivityInteractor {

    private String activityName;
    private String language;

    public GetDescriptionByActivityInteractor(String activityName, String language) {
        this.activityName = activityName;
        this.language = language;
    }

    public interface  GetDescriptionByActivityInteractorResponse {
        public void response(Description description);
    }

    public void execute(final Context context, final GetDescriptionByActivityInteractor.GetDescriptionByActivityInteractorResponse completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DescriptionDao dao = new DescriptionDao(context);

                final List<Description> description = dao.query(activityName, language);

                MainThreadUtil.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.response(description.get(0));
                    }
                });


            }
        }).start();
    }
}
