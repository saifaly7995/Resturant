//package com.example.resturant;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.SearchView;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.resturant.MainActivity.lastid;
//
//public class FragmentHistory  extends Fragment {
//    View view;
//    RecyclerView lv;
//
//    private List<ModelHistory> listItems;
//    private Context context;
//
//    static final String url ="https://projectresturent.000webhostapp.com/cartlist.php?customer_id="+lastid;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.history,container,false);
//        lv=(RecyclerView) view.findViewById(R.id.history_list);
//
//        listItems=new ArrayList<>();
//        lv.setLayoutManager(new LinearLayoutManager(getContext()));
//
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//
//                        try {
//                            JSONObject jsonObject= new JSONObject(response);
//                            JSONArray jsonArray= jsonObject.getJSONArray("cartlist");
//
//                            for (int i=0;i<jsonArray.length();i++)
//                            {
//                                JSONObject o=jsonArray.getJSONObject(i);
//                                ModelHistory item = new ModelHistory(o.getInt("order_id"),o.getInt("total"),o.getString("datetime")));
//                                listItems.add(item);
//                            }
//
//                            lv.setAdapter(new CartAdapter(listItems,getContext()));
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getContext(), "execption", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(getContext(), "Net check kar"+error, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(getContext());
//        queue.add(stringRequest);
//
//        lv.setLayoutManager(new GridLayoutManager(getContext(),1));
//
//
//
//
//        return view;
//    }
//
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        inflater.inflate(R.menu.toolbar_menu,menu);
//        MenuItem search = menu.findItem(R.id.searchbar);
//        SearchView searchView=(SearchView)search.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//
//                Toast.makeText(getContext(), newText+"", Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//}
