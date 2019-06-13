package com.example.resturant;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.resturant.MainActivity.lastid;

public class ViewProduct extends AppCompatActivity {

    public static String totalpricee;
    FloatingActionButton fab;
    ImageView img;
    TextView tvname,tvprice,tvdesc,total;
    int minteger;
    int totalprice;
    int sumofall=0;
    String price;
    String suml="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        final Intent intent = getIntent();
        final String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        price = intent.getStringExtra("price");
        final String image = intent.getStringExtra("image");


         final int id=intent.getIntExtra("id",0);
        fab=findViewById(R.id.fab);
        img=(ImageView)findViewById(R.id.expandedImage);
        tvname=(TextView)findViewById(R.id.tv_name_selected);
        tvdesc=(TextView)findViewById(R.id.tv_desc_selected);
        tvprice=(TextView)findViewById(R.id.tv_selected_price);
        total=(TextView)findViewById(R.id.total_price);

        tvname.setText(name);
        tvprice.setText(price+"/= PKR ");
        tvdesc.setText(desc);
        total.setText(price+"/= PKR ");
        Picasso.get().load(image).into(img);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


if(minteger!=0) {
    String f = "https://projectresturent.000webhostapp.com/cartapi.php?car_id=" + 1 + "&customer_id=" + lastid + "&item_id=" + id + "&name=\"" + name + "\"&image=\"" + image + "\"&price=" + Integer.parseInt(price) + "&quantity=" + minteger + "&total_price=" + totalprice;


    // Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, f,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    if (response.equals("n")) {
                        Toast.makeText(ViewProduct.this, "" + response, Toast.LENGTH_SHORT).show();
                    } else {

                        totalpricee = response.trim();

                        Intent intent = new Intent(ViewProduct.this, Cart.class);

                        intent.putExtra("price", response.trim());


                        startActivity(intent);
                        finish();

                        Toast.makeText(ViewProduct.this, "lst : " + response, Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(ViewProduct.this, "Internet Failure", Toast.LENGTH_SHORT).show();
        }
    });


    // Add the request to the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(stringRequest);


}
else {
    Toast.makeText(ViewProduct.this, "Minimum Quantity is 1", Toast.LENGTH_SHORT).show();
}


            }
        });

        Toast.makeText(this, name+"\n"+image, Toast.LENGTH_SHORT).show();
    }

    public void gettotal(){

        String d="https://projectresturent.000webhostapp.com/totalapi.php?customer_id="+lastid;
        // Request a string response from the provided URL.
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, d,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response1) {
//                                 Display the first 500 characters of the response string.

                            Toast.makeText(ViewProduct.this, response1+"yeh hai", Toast.LENGTH_SHORT).show();

                            suml=response1.trim();


                        Toast.makeText(ViewProduct.this, suml+"<", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewProduct.this, "Internet Failure", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(this, suml+" <", Toast.LENGTH_SHORT).show();
        // Add the request to the RequestQueue.
        RequestQueue queue2 = Volley.newRequestQueue(getApplicationContext());
        queue2.add(stringRequest2);
    }

    public void decreaseInteger(View view) {
        minteger = minteger - 1;
        if (minteger < 1) {
            Toast.makeText(this, "Minimum Quantity is 1", Toast.LENGTH_LONG).show();
            minteger=1;
            display(minteger);
            totalprice=(minteger*(Integer.parseInt(price)));
            total.setText(totalprice+"/= PKR");
        }else{
            display(minteger);
            totalprice=(minteger*(Integer.parseInt(price)));
            total.setText(totalprice+"/= PKR");
        }
    }

    public void increaseInteger(View view) {

        minteger = minteger + 1;
        if (minteger > 30) {
            Toast.makeText(this, "For Big orders Please Call 00112233", Toast.LENGTH_LONG).show();
            minteger=30;
            display(minteger);
            totalprice=(minteger*(Integer.parseInt(price)));
            total.setText(totalprice+"/= PKR");
        }else{
            display(minteger);
            totalprice=(minteger*(Integer.parseInt(price)));
            total.setText(totalprice+"/= PKR");
        }

    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;

        finish();
    }
}
