package com.example.timeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddPost extends AppCompatActivity {
    EditText content;
    Button button;
    Calendar calendar; // IT GETS THE CALENDER IN BUILT FUNCTION
    SimpleDateFormat simpleDateFormat;  // FOR ADDING TODAYS DATE IN FORMATE
    String date;
    SharedPreferences preferences;  // DECLARED SHARED_PREFRANCES
    String id,name,postUser;
    ConnectDatabase connectDatabase = new ConnectDatabase(this);       // CONNECTED TO DATABASE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        calendar = Calendar.getInstance();      // TO GET THE DATE
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");    // FORMATE OF THE DATE
        date = simpleDateFormat.format(calendar.getTime());     // FINAL RESULT VALUE OF DATE
        content = findViewById(R.id.add_post_description);
        button = findViewById(R.id.post_button_view);

        // GETS THE USERS ID AND NAME WHICH HAS LOGGED IN
        preferences = getSharedPreferences("preference", Context.MODE_PRIVATE);
        id = preferences.getString("ID", "");
        name = preferences.getString("FirstName", "");

        // ADDED THE POST IN GENERAL TIMELINE SECTION
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postDescription = content.getText().toString();  // TAKES THE VALUE WE WANTED TO POST
                //Log.i("PostDescription::", postDescription);

                Post post = new Post(name,id,postDescription,date);
                connectDatabase.addPost(post);      // addPost() METHOD IN ConnectDatabase CLASS IS CALLED AND PASSED THE VALUE THROUGH "post"

                Intent intent = new Intent(AddPost.this,MyTimeline.class);
                startActivity(intent);

            }
        });
    }


}