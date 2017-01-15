package es.elprincipe.madridguide.manager.image;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DownloadImage {

    private WeakReference<Context> context;
    private List<String> listImage;
    private DownloadImageResponse completion;

    private List<ImageData> listImages;


    private DownloadImage() {
    }


    public interface DownloadImageResponse{
        public void response(boolean success);
    }

   /* public DownloadImage(List<String> url, Context ctx, final DownloadImageResponse response){

        this.context = new WeakReference<Context>(ctx);
        this.listImage = new CopyOnWriteArrayList<>(url);
        this.completion = response;

        existImageDirectory();

        for(String imgUrl : url){
            String fileName = new UrlFileName(imgUrl).fileName();
            imageDownload(this.context.get(),fileName,imgUrl);
        }

    }*/

    public DownloadImage(ImageDataList url, Context ctx, final DownloadImageResponse response){

        this.context = new WeakReference<Context>(ctx);
        this.listImages = new CopyOnWriteArrayList<>(url.allImage());
        this.completion = response;

        existImageDirectory();

        for(ImageData imgUrl : url.allImage()){
            imageDownload(this.context.get(),imgUrl);
        }

    }


    private void existImageDirectory() {
        String  path = new String(String.valueOf(this.context.get().getFilesDir()));
        File directory = new File(path + "/images");
        if (! directory.exists()){
            directory.mkdir();
            directory.getAbsolutePath();
        }
    }

    private void imageHasBeenDownload(ImageData imageData){


       listImages.remove(imageData);

        if (listImages == null){
            completion.response(true);
        }
        if (listImages.isEmpty()){


            completion.response(true);
        }



    }

    //save image
    private void imageDownload(Context ctx, final ImageData imageData){


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageData.getUrl());
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    File file = new File(DownloadImage.this.context.get().getFilesDir()+ "/images/" + imageData.getNameImage());
                    try {
                        file.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                        ostream.flush();
                        ostream.close();
                        imageHasBeenDownload(imageData);


                    } catch (IOException e) {
                        Log.e("IOException", e.getLocalizedMessage());
                        completion.response(false);

                    }

                } catch(IOException e) {
                    System.out.println(e);
                    Log.v(getClass().getName(), imageData.getNameImage());
                    completion.response(false);
                }
            }
        }).start();




    }




}
