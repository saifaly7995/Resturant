package com.example.resturant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
import java.util.Arrays;
import java.util.List;

import static com.example.resturant.Splash.prefs;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private android.support.v7.widget.Toolbar tb;
    private TextView tvnavusername;
    private NavigationView navigationView;
    TextView nametv;
    public static int lastid;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_exit:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    ViewPagerAdapter
            viewpageradapter;


    List<Modeluserdet> listuserdet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String email=prefs.getString("useremail","");
        String password=prefs.getString("userpass","");

//        NavigationView navigationView = (NavigationView) findViewById(R.id.navdrawer);
//        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
//        nametv=headerView.findViewById(R.id.tvnavheadername);
//        nametv.setText("sss");


        //navigation view
        navigationView= findViewById(R.id.navdrawer);
        View h = navigationView.inflateHeaderView(R.layout.nav_header);

        nametv=h.findViewById(R.id.tvnavheadername);



        String url="https://projectresturent.000webhostapp.com/userdetails.php?email=\""+email+"\"&password=\""+password+"\"+";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("userdetails");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject o=jsonArray.getJSONObject(i);
                                prefs.edit().putString("usernamedb",o.getString("name")).commit();

                                prefs.edit().putInt("useriddb",o.getInt("customer_id")).commit();

                                lastid=o.getInt("customer_id");
                                nametv.setText(o.getString("name"));
                                Toast.makeText(MainActivity.this, prefs.getString("usernamedb","")+"dd"+lastid, Toast.LENGTH_SHORT).show();
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Main Exception", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);


        navigationView.setNavigationItemSelectedListener(this);

        //drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayoutmain);
        tabLayout=(TabLayout)findViewById(R.id.tablayout_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        tb=  findViewById(R.id.toolbar_id);

        String newname=prefs.getString("usernamedb","");
        Toast.makeText(this, newname+"ss", Toast.LENGTH_SHORT).show();

        setSupportActionBar(tb);
        //drawer menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,tb,R.string.open,R.string.close);




        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();






        viewpageradapter= new ViewPagerAdapter(getSupportFragmentManager());
//tabs ka kaaam

        viewpageradapter.Addfragment(new FragmenttMain(), "MAIN");
        viewpageradapter.Addfragment(new FragmentPizza(), "PIZZA");
        viewpageradapter.Addfragment(new Fragmentfastfood(), "BURGER");
        viewpageradapter.Addfragment(new FragmentSnacks(), "DESI");
//        viewpageradapter.Addfragment(new FragmentDeals(), "DEALS");

        viewPager.setAdapter(viewpageradapter);
        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId())
            {
                case R.id.menu_main:
                    viewPager.setCurrentItem(0);
                    break;

                case R.id.menu_pizza:
                    viewPager.setCurrentItem(1);
                    break;

                case R.id.menu_burger:
                    viewPager.setCurrentItem(2);
                    break;


                case R.id.menu_desi:
                    viewPager.setCurrentItem(3);
                    break;

                case R.id.menu_deals:
                    viewPager.setCurrentItem(4);
                    break;

                case R.id.menu_logout:
                    prefs.edit().clear().commit();
                    Intent i=new Intent(MainActivity.this,Login.class);
                    startActivity(i);
                    finish();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;

        finish();
    }

}

