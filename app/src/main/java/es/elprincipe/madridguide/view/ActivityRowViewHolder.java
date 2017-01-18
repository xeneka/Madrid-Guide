package es.elprincipe.madridguide.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.ref.WeakReference;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.model.activity.Activity;
import es.elprincipe.madridguide.util.UrlFileName;


public class ActivityRowViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    ImageView logoImageView;
    ImageView backgroundImage;
    private WeakReference<Context> context;

    public ActivityRowViewHolder(View rowActivity){
        super(rowActivity);

        nameTextView = (TextView) rowActivity.findViewById(R.id.row_activity_name);
        logoImageView = (ImageView) rowActivity.findViewById(R.id.row_activity_logo);
        backgroundImage = (ImageView) rowActivity.findViewById(R.id.row_backgroundImageActivity);

        context = new WeakReference<Context>(rowActivity.getContext());
    }

    public void setActivity(final @NonNull Activity activity){

        if(activity == null){
            return;
        }

        nameTextView.setText(activity.getName());
        Picasso.with(context.get())
                .load(activity.getLogoImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(logoImageView);

        String  path = new String(String.valueOf(context.get().getFilesDir()));
        String fileName = new UrlFileName(activity.getImageUrl()).fileName();
        File fileimage = new File(path+"/images/"+fileName);

        Picasso.with(context.get())
                .load(fileimage)
                .into(backgroundImage);

    }
}
