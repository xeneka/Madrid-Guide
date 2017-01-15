package es.elprincipe.madridguide.manager.image;


import android.content.Context;

import java.io.File;
import java.lang.ref.WeakReference;

public class DeleteImageFromInternalStorage {

    private WeakReference<Context> context;

    private DeleteImageFromInternalStorageResponse completion;

    public interface DeleteImageFromInternalStorageResponse{
        public void completion(boolean success);
    }

    private DeleteImageFromInternalStorage(){

    }

    public DeleteImageFromInternalStorage(Context ctx, final DeleteImageFromInternalStorageResponse response){

        this.context = new WeakReference<Context>(ctx);
        final String  path = new String(String.valueOf(this.context.get().getFilesDir()));
        this.completion = response;


        new Thread(new Runnable() {
            @Override
            public void run() {

                File dir = new File(path+"/images");
                if (dir.isDirectory())
                {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++)
                    {
                        new File(dir, children[i]).delete();
                    }
                }

                response.completion(true);
            }
        }).start();





    }



}
