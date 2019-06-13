package com.example.resturant;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmenttMain extends Fragment {

    RecyclerView lv;

    private List<ListItem> listItemsrec;
    private List<ModelSlider> listItems= new ArrayList<>();;
    private Context context;
    SliderLayout sliderLayout;
    static final String url = "https://projectresturent.000webhostapp.com/dealsapi.php";
    static final String urlslider = "https://projectresturent.000webhostapp.com/dealsliderapi.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vview = inflater.inflate(R.layout.layoutmain, container, false);

        //recycler
        lv=(RecyclerView) vview.findViewById(R.id.main_list_id);

        listItemsrec=new ArrayList<>();
        lv.setLayoutManager(new LinearLayoutManager(getContext()));
        //

        sliderLayout = vview.findViewById(R.id.imageSlider);

        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();


        ///recyler

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("deals");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject o=jsonArray.getJSONObject(i);
                                ListItem item = new ListItem(o.getString("name"),o.getString("price"),o.getString("description")+"",o.getString("image"),o.getInt("item_id"));
                                listItemsrec.add(item);
                            }

                            lv.setAdapter(new AdapterMainn(listItemsrec,getContext()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "execption", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Net check kar"+error, Toast.LENGTH_SHORT).show();

            }
        });

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);

        lv.setLayoutManager(new GridLayoutManager(getContext(),2));


        ///


        return vview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.toolbar_menu,menu);
        MenuItem search = menu.findItem(R.id.searchbar);
        SearchView searchView=(SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                Toast.makeText(getContext(), newText+"", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setSliderViews() {






        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlslider,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("dealslider");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject o=jsonArray.getJSONObject(i);
                                ModelSlider modelSlider= new ModelSlider(o.getString("name"),o.getString("imageurl"));
                                listItems.add(modelSlider);
                            }


                            for (int j=0;j<jsonArray.length();j++){
                                SliderView sliderView= new DefaultSliderView(getContext());
                                ModelSlider md=listItems.get(j);

                                switch (j) {
                                    case 0:
                                        sliderView.setImageUrl(md.getUrl());
                                        sliderView.setDescription(md.getName());

                                        break;
                                    case 1:

                                        sliderView.setImageUrl(md.getUrl());
                                        sliderView.setDescription(md.getName());
                                        break;
                                    case 2:

                                        sliderView.setImageUrl(md.getUrl());
                                        sliderView.setDescription(md.getName());
                                        break;
                                    case 3:

                                        sliderView.setImageUrl(md.getUrl());
                                        sliderView.setDescription(md.getName());
                                        break;
                                }


                                sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);

                                final int finalI = j;
                                sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(SliderView sliderView) {
                                        Toast.makeText(getContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                                    }
                                });


                                //at last add this view in your layout :
                                sliderLayout.addSliderView(sliderView);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "execption", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Net check kar"+error, Toast.LENGTH_SHORT).show();

            }
        });

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }

}