package es.elprincipe.madridguide.model.activity;


public class Description {

    private String language;
    private String description;
    private long idActitivy;
    private long id;


    public Description(long id,String language, String description, long idActitivy) {
        this.language = language;
        this.description = description;
        this.idActitivy = idActitivy;
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public long getIdActitivy() {
        return idActitivy;
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


    public Description setIdActitivy(long idActitivy) {
        this.idActitivy = idActitivy;
        return this;
    }

    public Description setId(long id) {
        this.id = id;
        return this;
    }
}
