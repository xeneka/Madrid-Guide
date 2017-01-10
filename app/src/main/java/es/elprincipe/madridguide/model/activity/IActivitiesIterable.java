package es.elprincipe.madridguide.model.activity;


import java.util.List;

public interface IActivitiesIterable {

    long size();
    Activity get(long index);
    List<Activity> allActivities();
}
