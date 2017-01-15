package es.elprincipe.madridguide.model.activity;


public class Description {

    private String language;
    private String description;
    private String activityName;
    private long id;


    public Description(long id,String language, String description, String activityName) {
        this.language = language;
        this.description = description;
        this.activityName = activityName;
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getActivityName() {
        return activityName;
    }

    public long getId() {
        return id;
    }

    public Description setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Description setDescription(String description) {
        this.description = description;
        return this;
    }


    public Description setActivityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public Description setId(long id) {
        this.id = id;
        return this;
    }
}
