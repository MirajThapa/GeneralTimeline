package com.example.timeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText adminName,adminPassword;
    Button adminLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //SETS THE VARIABLE FOR USERNAME AND PASSWORD AND LOGIN BUTTON
        adminName =(EditText) findViewById(R.id.admin_login_name);
        adminPassword =(EditText) findViewById(R.id.admin_login_password);
        adminLoginBtn =(Button) findViewById(R.id.admin_login_btn);

        //ADMIN LOGIN
        adminLoginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = adminName.getText().toString();       // UI USERNAME VARIABLE READING
                String password = adminPassword.getText().toString();   // UI PASSWORD VARIABLE READING

                // IF THE ADMIN USERNAME AND PASSWORD CORRECT THEN PASSES TO "AdminActivity" ACTIVITY
                if(name.equals("admin") && password.equals("admin")){
                    Intent intent = new Intent(AdminLogin.this,AdminActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AdminLogin.this, "wrong passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}