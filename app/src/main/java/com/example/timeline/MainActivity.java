package com.example.timeline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText logUsername, logPassword;
    Button loginBtn;
    TextView registerPart, adminPart;


    ConnectDatabase db = new ConnectDatabase(this);  // CONNECTING TO DATABASE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TAKING THE SECTION FROM UI OR XML FILE
        logUsername = findViewById(R.id.editTextPersonName);
        logPassword = findViewById(R.id.editTextPassword2);
        loginBtn = findViewById(R.id.button2);
        adminPart = findViewById(R.id.main_admin_login);
        registerPart = findViewById(R.id.textView3);


//        SharedPreferences preferences = getSharedPreferences("preference", MODE_PRIVATE);
//
//        if (preferences.getString("ID", "") != "" && preferences.getString("Email", "") != "") { // code to understand
//            Intent intent = new Intent(MainActivity.this, MyTimeline.class);
//            startActivity(intent);
//        }

        // AFTER CLICKING THE LOGIN BUTTON
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TAKES THE FIRSTNAME AND PASSWORD FROM THE INPUT FIELD
                String user = logUsername.getText().toString();
                String pass = logPassword.getText().toString();

                // CURSOR IS USED TO TAKE THE DATA FROM THE DATABASE
                Cursor cursor = db.checkSignUp(user, pass);

                // IF THE USER IS AVAILABLE IT MAKES THE SHAREDPREFERENCES VARIABLE OF THE DATA OF USER WHICH HELPS US TO ACCESS IT TO DIFFERENT ACTIVITIES
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getColumnIndex("personId");
                        int firstName = cursor.getColumnIndex("firstName");
                        int surname = cursor.getColumnIndex("surname");
                        int email = cursor.getColumnIndex("email");
                        int address = cursor.getColumnIndex("address");
                        int updateDate = cursor.getColumnIndex("updateDate");
                        int reg_date = cursor.getColumnIndex("register_date");
                        int dob = cursor.getColumnIndex("dob");
                        int phone = cursor.getColumnIndex("phoneNumber");
                        int password = cursor.getColumnIndex("password");

                        // sharedPreferences points to a file containing key-value pairs and provides simple methods to read and write them
                        SharedPreferences preferences = getSharedPreferences("preference", MODE_PRIVATE);
                        preferences.edit().putString("ID", cursor.getString(id)).apply();
                        preferences.edit().putString("FirstName", cursor.getString(firstName)).apply();
                        preferences.edit().putString("Surname", cursor.getString(surname)).apply();
                        preferences.edit().putString("Address", cursor.getString(address)).apply();
                        preferences.edit().putString("Email", cursor.getString(email)).apply();
                        preferences.edit().putString("Register_date", cursor.getString(reg_date)).apply();
                        preferences.edit().putString("UpdateDate", cursor.getString(updateDate)).apply();
                        preferences.edit().putString("DOB", cursor.getString(dob)).apply();
                        preferences.edit().putString("Phone", cursor.getString(phone)).apply();
                       preferences.edit().putString("Password", cursor.getString(password)).apply();

                    } while (cursor.moveToNext()); // THUS IT ONLY EXECUTES ONCE
                }

                // checkSignUp() METHOD CHECKS WHETHER THE USER IS REGISTERED IN OR NOT
                Boolean confirmUser = cursor.getCount() > 0;
                // IF THE USER IS REGISTERED confirmUser will be 1..
                if (confirmUser) {
                    Toast.makeText(MainActivity.this, "Login successFull " + user, Toast.LENGTH_SHORT).show();
                    // IT PASSES US TO DIFFERENT MY TIMELINE ACTIVITY
                    Intent intent = new Intent(MainActivity.this, MyTimeline.class);
                    startActivity(intent);
                }
                // IF THE USER IS NOT REGISTERED INTO THE DATABASE OR INCORRENT CREDENTIALS
                else {
                    Toast.makeText(MainActivity.this, "Login Not successful " + user, Toast.LENGTH_SHORT).show();
                }
            }

        });
        // IF THERE IS NEW USER AND WANTS TO REGISTER HIS/HER PROFILE
        registerPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // PASSES TO REGISTRATION CLASS
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });

        // PART FOR ADMINISTATOR LOGIN
        adminPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AdminLogin.class);
                startActivity(intent);
            }
        });



    }
}