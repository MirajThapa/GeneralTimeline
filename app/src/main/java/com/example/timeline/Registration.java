package com.example.timeline;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Registration extends AppCompatActivity {
    // UI VARIABLES HAD BEEN DECLAERD
    EditText regFirstName,regSurname,regPhoneNumber,regAddress,regDateOfBirth,regEmail,regPassword;
    Button registerBtn;
    ConnectDatabase connectDatabase = new ConnectDatabase(this);    // CONNECTING TO DATABASE
    ImageView photo;
    Calendar calendar;      // TO GET THE DATE
    SimpleDateFormat simpleDateFormat;      // DATE FORMATE
    String imageUrl = "imageurl";
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // CONNECTS THE VARIABLES TO UI
        regFirstName = findViewById(R.id.editTextTextPersonNameFirstName);
        regSurname = findViewById(R.id.editTextTextPersonNameSurname);
        regPhoneNumber = findViewById(R.id.editTextNumber);
        regAddress = findViewById(R.id.editTextAddress);
        regDateOfBirth = findViewById(R.id.editTextDate);
        regEmail = findViewById(R.id.editTextTextEmailAddress);
        regPassword = findViewById(R.id.editTextTextPassword);
        photo = findViewById(R.id.imageView);

        calendar = Calendar.getInstance();      // TO GET THE DATE
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");    // FORMATE OF THE DATE
        date= simpleDateFormat.format(calendar.getTime());

        registerBtn = findViewById(R.id.button);

        // IT HELPS TO CHOOSE PHOTO FROM MOBILE
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallary = new Intent(Intent.ACTION_PICK);  // passing implicit intent ,, letting the user pick the photo
                iGallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  // to open the gallary section of mobile
                startActivityForResult(iGallary,100);  // passing code

            }
        });

        // TO REGISTER USER
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // GETS THE VALUE WRITTEN IN UI AND STORES THEM IN VARIABLES
                String firstname = regFirstName.getText().toString();
                String surname = regSurname.getText().toString();
                String phone = regPhoneNumber.getText().toString();
                String address = regAddress.getText().toString();
                String dob = regDateOfBirth.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();

                // TO CHECK IF EVERYTHING IS FILLED
                if(!firstname.isEmpty() && !surname.isEmpty() && !phone.isEmpty() && !address.isEmpty() && !dob.isEmpty() &&
                        !email.isEmpty() && !password.isEmpty()){
                    // OBJECT OF CLASS "Person" is made and passed the VALUE THROUGH PARAMETER TO STORE THEM IN DATABASE
                    Person r1 = new Person(firstname,surname,email,password,dob,phone,address,imageUrl,date);
                    connectDatabase.addIndividual(r1);
                }
                else{
                    Toast.makeText(Registration.this,"All fields are required",Toast.LENGTH_SHORT).show();
                }
                onBackPressed();    // AUTOMATICALLY THROWS US TO LOGIN ACTIVITY
            }
        });
    }

    // FOR IMAGE SELECTION
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // IF THE CODE MATCHED
        if(requestCode==100){
            if(data!=null){
                imageUrl = data.getData().toString();   // IMAGE TO STRING CONVERSION
                //Log.i("imagePath", imageUrl);
                photo.setImageURI(data.getData());  // SETS THE SELECTED PHOTO IN UI
            }
        }
    }

}