package es.elprincipe.madridguide.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.elprincipe.madridguide.R;
import es.elprincipe.madridguide.adapter.ShopsAdapter;
import es.elprincipe.madridguide.model.Shop;
import es.elprincipe.madridguide.model.Shops;
import es.elprincipe.madridguide.view.OnElementClick;


public class ShopsFragment extends Fragment {


    private RecyclerView shopRecyclerView;
    private ShopsAdapter adapter;
    private Shops shops;

    private OnElementClick<Shop> listener;

    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        shopRecyclerView = (RecyclerView) view.findViewById(R.id.shops_recycler_view);
        shopRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void updateUI() {
        adapter = new ShopsAdapter(shops, getActivity());
        shopRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnElementClick<Shop>() {
            @Override
            public void clickedOn(Shop shop, int position) {

                if (listener != null){
                    listener.clickedOn(shop,position);
                }

            }
        });



    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
        updateUI();
    }

    public OnElementClick<Shop> getListener() {
        return listener;
    }

    public void setListener(OnElementClick<Shop> listener) {
        this.listener = listener;
    }
}
