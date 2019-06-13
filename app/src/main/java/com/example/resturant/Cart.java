package com.example.resturant;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.List;

import static com.example.resturant.MainActivity.lastid;

public class Cart extends FragmentActivity {


    FrameLayout frameLayout;
    Button btnchck,btncont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        frameLayout=findViewById(R.id.framecart);
        TextView tv= findViewById(R.id.tvcart);
        TextView tvcurrency=findViewById(R.id.tvcurrency);
        btnchck=(Button)findViewById(R.id.btnchckout);
        btncont=(Button)findViewById(R.id.btncont);
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        if(price.equals("0")||price.trim().equals(""))
        {
            tv.setText("0 items");
            tvcurrency.setText("");
        }
        else
            {
                tvcurrency.setText("PKR");
                tv.setText(price+"/=");
            }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framecart,new FragmentCart());
        ft.commit();

        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Cart.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnchck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d="https://projectresturent.000webhostapp.com/delcart.php?customer_id="+lastid;
                // Request a string response from the provided URL.
                StringRequest stringRequest2 = new StringRequest(Request.Method.GET, d,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response1) {
//                                 Display the first 500 characters of the response string.
                                Intent intent = new Intent(Cart.this,Cart.class);
                                intent.putExtra("price","0");
                                startActivity(intent);
                                Toast.makeText(Cart.this, "Order Place Successfully", Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Cart.this, "Internet Failure", Toast.LENGTH_SHORT).show();
                    }
                });


                // Add the request to the RequestQueue.
                RequestQueue queue2 = Volley.newRequestQueue(getApplicationContext());
                queue2.add(stringRequest2);
            }
        });

    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent i= new Intent(Cart.this,MainActivity.class);
        startActivity(i);
        finish();
    }

}




