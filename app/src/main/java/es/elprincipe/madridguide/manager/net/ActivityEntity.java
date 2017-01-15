package es.elprincipe.madridguide.manager.net;


import com.google.gson.annotations.SerializedName;

public class ActivityEntity {

    @SerializedName("id") private Long id;
    @SerializedName("name") private String name;
    @SerializedName("img") private String img;
    @SerializedName("logo_img") private String logimg;
    @SerializedName("telephone") private String telephone;
    @SerializedName("email") private String email;
    @SerializedName("url") private String url;
    @SerializedName("address") private String address;
    @SerializedName("description_en") private String description_en;
    @SerializedName("description_es") private String description_es;
    @SerializedName("gps_lat") private Float gps_lat;
    @SerializedName("gps_lon") private Float gps_lon;
    @SerializedName("special_offer") private String special_offer;
    //@SerializedName("categories") private String categories;
    //@SerializedName("rutas") private String rutas;
    @SerializedName("booking_operation") private String booking_operation;
    @SerializedName("booking_data") private String booking_data;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getLogimg() {
        return logimg;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription_en() {
        return description_en;
    }

    public String getDescription_es() {
        return description_es;
    }

    public Float getGps_lat() {
        return gps_lat;
    }

    public Float getGps_lon() {
        return gps_lon;
    }

    public String getSpecial_offer() {
        return special_offer;
    }

    /*public String getCategories() {
        return categories;
    }*/

    /*public String getRutas() {
        return rutas;
    }*/

    public String getBooking_operation() {
        return booking_operation;
    }

    public String getBooking_data() {
        return booking_data;
    }
}
