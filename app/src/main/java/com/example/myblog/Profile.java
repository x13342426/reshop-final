package com.example.myblog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;

public class Profile extends HomeActivity {


    //declaring buttons
    Button Clothes;
    Button View;
    Button AR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //info on this page is taken from layout and strings
        setContentView(R.layout.profile);

        // setting button resource location
        Clothes = findViewById(R.id.Clothes);
        View = findViewById(R.id.View);
        AR = findViewById(R.id.AR);
    }

    //when these buttons are clicked they will go to a new activity
    public void onButtonClick(View v) {
        if (v.getId() == R.id.Clothes) {
            Intent i = new Intent(Profile.this, Home.class);
            startActivity(i);

        }
        if (v.getId() == R.id.View) {
            Intent i = new Intent(Profile.this, PostDetailActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.AR) {
            Intent i = new Intent(Profile.this,Profile.class);
            startActivity(i);
        }
    }

}
