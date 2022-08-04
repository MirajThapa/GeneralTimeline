package com.example.timeline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyTimeline extends AppCompatActivity {
    // VARIABLES FOR THE UI SECTION
    ImageView profileImageView;
    TextView logoutBtn;
    Button addPost, myPost;
    RecyclerView recyclerView;      // TO SHOWS THE ADDED POST IN USER INTERFACE LIMITLESSLY
    ArrayList<Post> postHolder;     // ARRAYLIST TO HOLD THE POSTS
    SharedPreferences postPreferences;

    ConnectDatabase db = new ConnectDatabase(this);     // CONECTED TO DATABASE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_timeline);

        recyclerView = findViewById(R.id.my_timeline_recycler_view);        // VARIABLE DECLARED FOR RECYCLERVIEW SECTION
        recyclerView.setLayoutManager(new LinearLayoutManager(this));// TO SHOW ALL THE POST IN LINEAR MANNER
        postHolder = new ArrayList<Post>();     //DECLARED NEW MEMORY FOR THE POSTS
        Cursor cursor = db.readPost();      // CURSOR READS ALL THE DATAS FROM THE DATABASE

        // UNTIL THE POST FINISHED IT ADDS THE POST INTO THE POSTHOLDER ARRAY FROM DATABASE
        while (cursor.moveToNext()){
            Post postObj = new Post(cursor.getString(2),cursor.getString(3),cursor.getString(4));   // OBJECT OF POST
            postHolder.add(postObj);
        }

        // MYADAPTER CLASS PUTS ALL THE POST INSIDE OF THE RECYCLERVIEW SECTION
        MyAdapter myAdapter = new MyAdapter(postHolder);
        recyclerView.setAdapter(myAdapter);

        profileImageView = findViewById(R.id.timeline_profile_image_view);
        logoutBtn = findViewById(R.id.logout_profile_view);
        addPost = findViewById(R.id.timeline_add_post_button);
        myPost = findViewById(R.id.my_timeline_my_post);

        // THIS WILL TAKES US TO THE PROFILE SECTION ACTIVITY
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyTimeline.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // THIS WILL TAKES US TO THE ADD POST SECTION
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyTimeline.this, AddPost.class);
                startActivity(intent);
            }
        });

        // FOR LOGOUT
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("preferences",MODE_PRIVATE);
                preferences.edit().clear().apply();
                finish();
            }
        });

        // THIS WILL TAKES US TO THE POSTS THAT WE HAVE ADDED
        myPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyTimeline.this,MyPost.class);
                startActivity(intent);
            }
        });
    }

}