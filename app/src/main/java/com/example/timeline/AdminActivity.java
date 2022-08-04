package com.example.timeline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;      // RECYCLERVIEW IS THERE TO LIST THE ALL USERS IN THE DATABASE
    ArrayList<Person> allUsers;     // TO PUT THE LIST OF THE ARRAYS OF PERSON CLASS IN VARIABLE
    ConnectDatabase connectDatabase = new ConnectDatabase(this);        // CONNECTING TO DATABASE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerView = findViewById(R.id.admin_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allUsers = new ArrayList<Person>();     // ALLOCATE MEMORY FOR THE LIST OF THE USERS
        Cursor cursor = connectDatabase.readAllUser();      // TO READ ALL THE USERS IN THE DATABASE

        // ADD USERS IN ARRAY UNTIL THE USER LIST IS EMPTY
        while(cursor.moveToNext()){
            Person person = new Person(cursor.getString(0),cursor.getString(1),cursor.getString(3));    // POSITIONS ARE TAKEN FROM THE DATABASE
            allUsers.add(person);
        }

        // OBJECT OF "AdminAdapter" CLASS TO PASS ALL THE USERS
        AdminAdapter adminAdapter = new AdminAdapter(allUsers,AdminActivity.this);
        recyclerView.setAdapter(adminAdapter);  // SETS ALL THE USERS IN THE UI

    }
}