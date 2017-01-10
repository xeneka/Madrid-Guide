package es.elprincipe.madridguide.model.activity;


public interface IActivitiesUpdatable {
    void add(Activity activity);
    void delete(Activity activity);
    void edit(Activity newActivity, long index);
}