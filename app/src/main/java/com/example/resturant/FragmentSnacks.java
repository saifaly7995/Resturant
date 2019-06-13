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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentSnacks  extends Fragment {
    View view;
    RecyclerView lv;

    private List<ListItem> listItems;
    private Context context;

    static final String url ="https://projectresturent.000webhostapp.com/desiapi.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.snacks,container,false);
        lv=(RecyclerView) view.findViewById(R.id.snack_list_id);

        listItems=new ArrayList<>();
        lv.setLayoutManager(new LinearLayoutManager(getContext()));


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("desi");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject o=jsonArray.getJSONObject(i);
                                ListItem item = new ListItem(o.getString("name"),o.getString("price"),o.getString("description")+"",o.getString("image"),o.getInt("item_id"));
                                listItems.add(item);
                            }

                            lv.setAdapter(new AdapterSnacks(listItems,getContext()));

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

        lv.setLayoutManager(new GridLayoutManager(getContext(),1));




        return view;
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

}


