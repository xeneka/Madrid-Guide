package es.elprincipe.madridguide.util;


import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class UrlFileName {


        private  String mImgUrl;

        public  UrlFileName(String imgUrl) {
            mImgUrl = imgUrl;
        }

        public  String fileName() {
            try{
                String urlFile = new URL(mImgUrl).getFile().toString();
                return urlFile.substring(urlFile.lastIndexOf('/')+1);
            }catch (IOException e){
                Log.e("IOException", e.getLocalizedMessage());
            }
            return null;
        }


}
