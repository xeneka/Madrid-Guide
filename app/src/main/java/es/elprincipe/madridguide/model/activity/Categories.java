package es.elprincipe.madridguide.model.activity;


public class Categories {
    public int id;
    public String categoryName;

    public Categories(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
