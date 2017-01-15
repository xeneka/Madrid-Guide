package es.elprincipe.madridguide.manager.image;


public class ImageData {

    private String url;
    private String nameImage;

    public ImageData(String url, String nameImage) {
        this.url = url;
        this.nameImage = nameImage;
    }

    public String getUrl() {
        return url;
    }

    public String getNameImage() {
        return nameImage;
    }
}
