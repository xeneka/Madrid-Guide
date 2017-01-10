package es.elprincipe.madridguide.manager.net;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.List;

import es.elprincipe.madridguide.R;

public class NetworkManagerforActivity {



    public interface GetActivityListener{
        public void getActivitiesEntitySuccess(List<ActivityEntity> result);
        public void getActivitiesentitiesDidFail();
    }

    WeakReference<Context> context;

    public NetworkManagerforActivity(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public void getActivitiesFromServer(final NetworkManagerforActivity.GetActivityListener listener){

        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = context.get().getString(R.string.activity_url);

        StringRequest request = new StringRequest(url,

                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        List<ActivityEntity> activityResponse = parseResponse(response);

                        if(listener != null){
                            listener.getActivitiesEntitySuccess(activityResponse);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null) {
                            listener.getActivitiesentitiesDidFail();
                        }
                    }
                }
        );
        queue.add(request);

    }

    private List<ActivityEntity> parseResponse(String response) {
        List<ActivityEntity> result = null;
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();
            ActivityResponse activityResponse = gson.fromJson(reader,ActivityResponse.class);
            result = activityResponse.result;


        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
