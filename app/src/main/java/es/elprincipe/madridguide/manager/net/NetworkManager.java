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

public class NetworkManager {




    public interface GetShopListener{
        public void getShopsEntitySuccess(List<ShopEntity> result);
        public void getShopsentitiesDidFail();
    }

    WeakReference<Context> context;

    public NetworkManager(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public void getShopsFromServer(final GetShopListener listener){

        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = context.get().getString(R.string.shops_url);

        StringRequest request = new StringRequest(url,

                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        //Log.d("JSON", response);
                        List<ShopEntity> shopResponse = parseResponse(response);

                        if(listener != null){
                            listener.getShopsEntitySuccess(shopResponse);
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null) {
                            listener.getShopsentitiesDidFail();
                        }
                    }
                }

        );
        queue.add(request);



    }

    private List<ShopEntity> parseResponse(String response) {
        List<ShopEntity> result = null;
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();
            ShopResponse shopResponse = gson.fromJson(reader,ShopResponse.class);
            result = shopResponse.result;


        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
