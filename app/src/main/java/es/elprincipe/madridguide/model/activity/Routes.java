package es.elprincipe.madridguide.model.activity;


public class Routes {
    public int id;
    public String route;

    public Routes(int id, String route) {
        this.id = id;
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public int getId() {

        return id;
    }
}
