package com.example.timeline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Profile_DAO extends AppCompatActivity {
    // UI VARIABLES ARE DECLARED
    private EditText f_name, s_name, e_mail, _phone, _dob, _address;
    ImageView _thumbnail, _backButton;
    Button updateButton;
    TextView update_date;
    Calendar calendar;      // TO GET THE DATE
    SimpleDateFormat simpleDateFormat;      // TO FORMAT THE DATE
    String date, id, password;
    SharedPreferences preferences;      // TO GET THE LOGGED USER
    ConnectDatabase connectDatabase = new ConnectDatabase(this);    // CONNECTION TO DATABASE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_user);

        calendar = Calendar.getInstance();      // TO GET THE DATE
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");    // FORMATE OF THE DATE
        date = simpleDateFormat.format(calendar.getTime());

        f_name = findViewById(R.id.name_edit_text);
        s_name = findViewById(R.id.editTextTextPersonName9);
        e_mail = findViewById(R.id.editTextTextEmailAddress2);
        _phone = findViewById(R.id.editTextPhone);
        _dob = findViewById(R.id.editTextDate2);
        _address = findViewById(R.id.editTextTextPersonName10);
        update_date= findViewById(R.id.last_update_view);

        updateButton = findViewById(R.id.layout_edit_user_button);
        _backButton = findViewById(R.id.edit_back_image_view);
        preferences = getSharedPreferences("preference", Context.MODE_PRIVATE);     // GETS THE DECLARED PREFERENCES
        setUserData();      // FUNCTION TO SET THE UPDATED DATA IN VIEW

        // UPDATES THE DATA
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // GETS THE VALUE OF DATA IN STRING
                String firstName = f_name.getText().toString();
                String surname = s_name.getText().toString();
                String email = e_mail.getText().toString();
                String phone = _phone.getText().toString();
                String dob = _dob.getText().toString();
                String address = _address.getText().toString();
                String updateDate = date;

                // OBJECT OF "Person" CLASS TO PASS THE VARIABLES THROUGH PARAMETERS
                Person person = new Person(id, firstName, surname, email, password, dob, phone, address, "photo", date,updateDate);
                // PASSES THE PARAMETERS AND SETS THE VALUE OF THE DATA AND UPDATE THEM
                person.setFirstName(firstName);
                person.setSurname(surname);
                person.setEmailAddress(email);
                person.setPhoneNumber(phone);
                person.setDate_of_birth(dob);
                person.setAddress(address);
                person.setPassword(password);
                person.setUpdateDate(date);
               // Log.i(":::", date);

                connectDatabase.updateProfile(person);      // UPDATE THE PROFILE FUNCTION IS CALLED BY PASSING PARAMETER
                updateSharedPreference();

                // TO UPDATE THE NAME IN POST SECTION
                Post post = new Post(id,firstName);
                post.setName(firstName);
                connectDatabase.updatePostUserName(post);

                onBackPressed();    // THROWS TO PREVIOUS ACTIVITY
            }
        });
        _backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    // SETS THE UPDATED DATA IN THE UI IMMEDIATLY
    public void setUserData() {
        id = preferences.getString("ID", "");
        password = preferences.getString("Password", "");
        f_name.setText(preferences.getString("FirstName", ""));
        s_name.setText(preferences.getString("Surname", ""));
        e_mail.setText(preferences.getString("Email", ""));
        _phone.setText(preferences.getString("Phone", ""));
        _dob.setText(preferences.getString("DOB", ""));
        _address.setText(preferences.getString("Address", ""));
        update_date.setText("Last Updated:  "+preferences.getString("UpdateDate", ""));
    }

    // UPDATES THE PREFERENCES VALUE
    public void updateSharedPreference() {
        preferences.edit().putString("FirstName", f_name.getText().toString()).apply();
        preferences.edit().putString("Surname", s_name.getText().toString()).apply();
        preferences.edit().putString("Address", _address.getText().toString()).apply();
        preferences.edit().putString("Email", e_mail.getText().toString()).apply();
        preferences.edit().putString("UpdateDate", update_date.getText().toString()).apply();
        preferences.edit().putString("DOB", _dob.getText().toString()).apply();
        preferences.edit().putString("Phone", _phone.getText().toString()).apply();
        preferences.edit().putString("UpdateDate", date).apply();
    }
}
