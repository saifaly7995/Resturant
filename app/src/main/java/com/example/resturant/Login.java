package com.example.resturant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.example.resturant.Splash.prefs;

public class Login extends AppCompatActivity {

    TextView btncreate;
    String stemail,stpass;
    EditText email,pass;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin=findViewById(R.id.btn_login);

        email=findViewById(R.id.etemaillog);
        pass=findViewById(R.id.etpasswordlog);
        btncreate=findViewById(R.id.tvbtnsignup);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                stemail=email.getText().toString().trim();
                stpass=pass.getText().toString().trim();


                String url="https://projectresturent.000webhostapp.com/loginapi.php?email=\""+stemail+"\"&password=\""+stpass+"\"+";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                if(response.trim().equals("y"))
                                {
                                    prefs.edit().putBoolean("notloggedin",false).commit();
                                    prefs.edit().putString("useremail",stemail).commit();
                                    prefs.edit().putString("userpass",stpass).commit();
                                    Intent i = new Intent(Login.this,MainActivity.class);
                                    v.getContext().startActivity(i);
                                    finish();

                                }
                                else
                                    {
                                        Toast.makeText(Login.this, "Invalid Id or Password", Toast.LENGTH_SHORT).show();

                                    }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(stringRequest);


            }
        });
    }
}
