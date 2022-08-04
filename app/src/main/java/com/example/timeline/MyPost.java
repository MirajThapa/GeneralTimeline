package com.example.timeline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MyPost extends AppCompatActivity implements MyPostAdapter.OnEditListener{
    // UI VARIABLES DECLARED
    ImageView backButton;
    RecyclerView recyclerView;
    ArrayList<Post> postHolder; // ARRAYLIST TO HOLD ALL THE POSTS FROM DATABASE
    SharedPreferences preferences;  // TO GET THE IDEA OF WHICH USER LOGGED IN PARTICULAR TIME
    ConnectDatabase connectDatabase = new ConnectDatabase(this);    // DATABASE CONNECTION

    AlertDialog alertDialog;    // DIALOG BOX WAS REQUIRED TO EDIT THE POST
    ConnectDatabase db = new ConnectDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        preferences = getSharedPreferences("preference", Context.MODE_PRIVATE); // CALLED SHAREDPREFERENCES
        String user_id = preferences.getString("ID", "");  // GETS THE ID OF LOGGED IN USER
        recyclerView = findViewById(R.id.my_post_section);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));   // SETS ALL THE POST IN LINEAR FORMAT
        postHolder = new ArrayList<Post>();     // NEW MEMORY ALLOCATED FOR ALL THE POSTS
        Cursor cursor = db.readMyAllPost(user_id);  // GETS THE POSTS FROM THE DATABASE

        // ADD POSTS INTO ARRAY UNTIL ALL POSTS ARE READ
        while (cursor.moveToNext()){
            Post postObj = new Post(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            postHolder.add(postObj);
        }

        // OBJECT OF "MyPostAdapter" IS MADE TO PUSH THE POSTS
        MyPostAdapter myAdapter = new MyPostAdapter(postHolder, this::onClickEdit, MyPost.this);
        recyclerView.setAdapter(myAdapter);     // TO ADD ALL THE POST IN UI SECTION

        backButton = findViewById(R.id.my_post_back_btn);
        // BACK BUTTON THROUGH INTENT
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MyPost.this,MyTimeline.class);
                startActivity(intent);
            }
        });
    }

    // PREVIOUSLY, ITS INTERFACE WAS MADE TO EDIT THE POST
    @Override
    public void onClickEdit(Post post, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);    // ALLOCATE NEW MEMORY FOR DIALOG BOX
        View view = LayoutInflater.from(this).inflate(R.layout.edit_post,null);

        EditText desc =view.findViewById(R.id.edit_post_description);
        MaterialButton update_post =view.findViewById(R.id.edit_button_view);
        desc.setText(postHolder.get(position).getDescription());        // SETS THE DESCRIPTION WHICH NEEDS TO BE UPDATED
        //Log.i("User Name::", postHolder.get(position).getName());

        builder.setView(view);      // DIALOG BOX SHOWS UP
        builder.setCancelable(true);    // CAN CANCEL THE DIALOG BOX

        // UPDATES THE DATABASE
        update_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _desc = desc.getText().toString();
                connectDatabase.editPost(postHolder.get(position).getPostId(),_desc);   // UPDATE DATABES IN POST TABLE WHERE POST ID IS GIVEN
                Intent intent = new Intent(MyPost.this,MyTimeline.class);
                startActivity(intent);
                //Log.i("desc::", _desc);
            }
        });

        alertDialog = builder.create();     // CREATED DIALOG BOX
        alertDialog.show();


    }
}