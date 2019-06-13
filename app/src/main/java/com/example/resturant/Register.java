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

public class Register extends AppCompatActivity {

    TextView btnsignin;
    Button signup;
    EditText fname,lname,contact,email,password,address;
    String stfname,stlname,stcontact,stemail,stpassword,staddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signup=findViewById(R.id.btn_register);



        fname=findViewById(R.id.et_fname);
        lname=findViewById(R.id.et_lname);
        email=findViewById(R.id.et_reg_email);
        contact=findViewById(R.id.etcontact);
        password=findViewById(R.id.aa);
        address=findViewById(R.id.ettaddress);

        btnsignin=findViewById(R.id.tvbtnsigin);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                stfname=fname.getText().toString().trim();
                stlname=lname.getText().toString().trim();
                stcontact=contact.getText().toString().trim();
                stemail=email.getText().toString().trim();
                stpassword=password.getText().toString().trim();
                staddress=address.getText().toString().trim();


                String url ="https://projectresturent.000webhostapp.com/registrationuserapi.php?first_name=\""+stfname+"\"&last_name=\""+stlname+ "\"&email=\""+stemail+"\"&contact=\""+stcontact+"\"&password=\""+stpassword+"\"&address=\""+staddress+"\"+";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                if(response.equals("register"))
                                {
                                    Toast.makeText(Register.this, "Regsitered", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Register.this,Login.class);
                                    v.getContext().startActivity(i);
                                    finish();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(stringRequest);

            }
        });



        }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;

        finish();
    }

        }
