package com.example.resturant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class Splash extends AppCompatActivity {

    public static SharedPreferences prefs;

    LinearLayout li,l2;
    Animation downtoup,topdown;
    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = getSharedPreferences("com.example.resturant", MODE_PRIVATE);
        l2=findViewById(R.id.l2);
        li=findViewById(R.id.layoutbuttons);
        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);
        topdown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        li.setAnimation(downtoup);
        l2.setAnimation(topdown);
        login=findViewById(R.id.btnsignin);
        signup=findViewById(R.id.bttnsignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Splash.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().putBoolean("firstrun", false).commit();
                Intent intent= new Intent(Splash.this,Login.class);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs

        }
    }
}
