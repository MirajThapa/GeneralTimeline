package com.example.timeline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    // UI VARIABLES ARE DECLARED
    TextView textView1, textView2, textView3, textView4, textView5, textView6;
    ImageView imageView, profilePicView, backBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("preference", MODE_PRIVATE);     // PREFERENCES TO GET THE LOGGED USER DATA

        textView1 = findViewById(R.id.profile_name_text_view);
        textView2 = findViewById(R.id.Email_text_view);
        textView3 = findViewById(R.id.t6);
        textView4 = findViewById(R.id.t8);
        textView5 = findViewById(R.id.t10);
        textView6 = findViewById(R.id.t12);
        imageView = findViewById(R.id.profile_image_view);
        backBtn = findViewById(R.id.profile_back_button_view);
        profilePicView = findViewById(R.id.profile_edit_image_view);

        // EDIT THE PROFILE
        profilePicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // PASS TO OTHER ACTIVITY
                Intent intent = new Intent(ProfileActivity.this,Profile_DAO.class);
                startActivity(intent);
            }
        });

        // THROWS US TO PREVIOUS ACTIVITY
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUserData();
    }

    // SETS THE DATA IN THE PROFILE ACTIVITY
    public void setUserData() {
        // VARIABLES TO GET THE UPDATED DATA OF THE USER
        String name = preferences.getString("FirstName", "");
        String name2 = preferences.getString("Surname", "");
        String id = preferences.getString("ID", "");
        String reg_date = preferences.getString("Register_date", "");
        String email = preferences.getString("Email", "");
        String address = preferences.getString("Address", "");
        String phone = preferences.getString("Phone", "");
        String dob = preferences.getString("DOB", "");

        // SETS THE UPDATED VALUE IN UI SECTION
        textView1.setText(name + " " + name2);
        textView2.setText(email);
        textView3.setText(address);
        textView4.setText(phone);
        textView5.setText(dob);
        textView6.setText(reg_date);
    }
}