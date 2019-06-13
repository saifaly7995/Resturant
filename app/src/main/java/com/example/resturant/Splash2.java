package com.example.resturant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.resturant.Splash.prefs;

public class Splash2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        prefs = getSharedPreferences("com.example.resturant", MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            Intent i = new Intent(Splash2.this,Splash.class);
            startActivity(i);
            finish();
            // using the following line to edit/commit prefs
//            prefs.edit().putBoolean("firstrun", false).commit();
        }else {
            if (prefs.getBoolean("notloggedin", true)) {
                Intent i = new Intent(Splash2.this, Login.class);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(Splash2.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
}
