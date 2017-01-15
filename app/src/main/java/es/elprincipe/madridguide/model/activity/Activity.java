package es.elprincipe.madridguide.model.activity;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Activity implements Serializable {

    private long id;
    private String name;
    private String imageUrl;
    private String logoImgUrl;
    private List<Description> descriptions;
    private String url;
    private String telephone;
    private String email;
    private String address;
    private float latitude;
    private float longitude;
    private LinkedList<Categories> categories;
    private LinkedList<Routes> routes;
    private String booking_operation;
    private String booking_data;

    public Activity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Activity(){

    }


    public long getId() {
        return id;
    }

    public Activity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Activity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public Activity setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
        return this;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public Activity setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Activity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Activity setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Activity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Activity setAddress(String address) {
        this.address = address;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Activity setLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Activity setLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public LinkedList<Categories> getCategories() {
        return categories;
    }

    public Activity setCategories(LinkedList<Categories> categories) {
        this.categories = categories;
        return this;
    }

    public LinkedList<Routes> getRoutes() {
        return routes;
    }

    public Activity setRoutes(LinkedList<Routes> routes) {
        this.routes = routes;
        return this;
    }

    public String getBooking_operation() {
        return booking_operation;
    }

    public Activity setBooking_operation(String booking_operation) {
        this.booking_operation = booking_operation;
        return this;
    }

    public String getBooking_data() {
        return booking_data;
    }

    public Activity setBooking_data(String booking_data) {
        this.booking_data = booking_data;
        return this;
    }

    public int getCountCartegories(){
        return categories.size();
    }

    public int getCountRoutes(){
        return routes.size();
    }
}
