package es.elprincipe.madridguide.manager.image;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class ImageDataList {

    List<ImageData> imageList;

    private ImageDataList(List<ImageData> imageList) {
        this.imageList = imageList;
    }


    public static @NonNull
    ImageDataList build(@NonNull  List<ImageData> imageDataList){

        ImageDataList imagesData = new ImageDataList(imageDataList);

        if(imagesData == null){
            imagesData.imageList = new ArrayList<>();
        }

        return imagesData;
    }


    public static ImageDataList build(){
        return build(new ArrayList<ImageData>());
    }



    public long size() {
        return imageList.size();
    }


    public ImageData get(long index) {
        return imageList.get((int) index);
    }


    public List<ImageData> allImage() {
        return imageList;
    }


    public void add(ImageData image) {

        imageList.add(image);

    }


    public void delete(ImageData imageData) {

        imageList.remove(imageData);
    }


    public void edit(ImageData newImage, long index) {

        imageList.set((int) index , newImage);
    }

}
