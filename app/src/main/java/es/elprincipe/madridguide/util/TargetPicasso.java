package es.elprincipe.madridguide.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;

public class TargetPicasso implements  Target{

    private WeakReference<Context> context;

    public TargetPicasso(final Context context) {
        this.context = new WeakReference<Context>(context);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        Log.v(getClass().getName(),"Imagen cargada..");
        //SaveFile.SaveFile(context.get(),url,bitmap);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        Log.v(getClass().getName(), "FALLO");
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        Log.v(getClass().getName(), "PRECARGA");

    }
}
