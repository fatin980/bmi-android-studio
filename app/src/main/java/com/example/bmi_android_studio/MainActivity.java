package com.example.bmi_android_studio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_home:

                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case  R.id.nav_aboutus:
                        Intent intent2 = new Intent(MainActivity.this, aboutus.class);
                        startActivity(intent2);

                        startActivity(intent2);
                        break;
                    //                 }

                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
    }

    //Main activity class start here
    public class MainActivity2 extends Activity {

        //Define layout
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

// Get the references to the widgets
            final EditText e1 = (EditText) findViewById(R.id.et1);
            final EditText e2 = (EditText) findViewById(R.id.et2);
            final TextView tv4 = (TextView) findViewById(R.id.tv4);

            findViewById(R.id.ib1).setOnClickListener(new View.OnClickListener() {

                // Logic for validation, input can't be empty
                @Override
                public void onClick(View v) {

                    String str1 = e1.getText().toString();
                    String str2 = e2.getText().toString();

                    if(TextUtils.isEmpty(str1)){
                        e1.setError("Please enter your weight");
                        e1.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(str2)){
                        e2.setError("Please enter your height");
                        e2.requestFocus();
                        return;
                    }

//Get the user values from the widget reference
                    float weight = Float.parseFloat(str1);
                    float height = Float.parseFloat(str2)/100;

//Calculate BMI value
                    float bmiValue = calculateBMI(weight, height);

//Define the meaning of the bmi value
                    String bmiInterpretation = interpretBMI(bmiValue);

                    tv4.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));

                }
            });

        }

        //Calculate BMI
        private float calculateBMI (float weight, float height) {
            return (float) (weight / (height * height));
        }

        // Interpret what BMI means
        private String interpretBMI(float bmiValue) {

            if (bmiValue < 16) {
                return "Severely underweight";
            } else if (bmiValue < 18.5) {

                return "Underweight";
            } else if (bmiValue < 25) {

                return "Normal";
            } else if (bmiValue < 30) {

                return "Overweight";
            } else {
                return "Obese";
            }
        }
    }


        public void setUpToolbar() {
            drawerLayout = findViewById(R.id.drawerLayout);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

        }

    }