package com.example.secondlifetesting;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.secondlifetesting.DBHelper;
import java.util.regex.Pattern;

        public class RegisterActivity extends AppCompatActivity {
            EditText phone, email, pwd;
            DBHelper DB;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_register);

                phone = (EditText) findViewById(R.id.Phone);
                email = (EditText) findViewById(R.id.email);
                pwd = (EditText) findViewById(R.id.Password);
                DB = new DBHelper(this);


            }

            @RequiresApi(api = Build.VERSION_CODES.FROYO)
            public void home(View view) {


                String phoneno = phone.getText().toString();
                String emailid = email.getText().toString();
                String pass = pwd.getText().toString();
                String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String checkPassword = "^" +
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$";


                if (phoneno.equals("") || emailid.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else if (!emailid.matches(checkEmail)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email.", Toast.LENGTH_SHORT).show();

                } else if (!pass.matches(checkPassword)) {
                    Toast.makeText(getApplicationContext(), "Password is too weak.", Toast.LENGTH_SHORT).show();

                } else if (!(phoneno.length() > 6 && phoneno.length() <= 13)) {
                    Toast.makeText(getApplicationContext(), "Invalid Phone Number.", Toast.LENGTH_SHORT).show();
                } else {
                    DB.insertData(emailid.trim(),
                            pass.trim(),
                            phoneno.trim());
                    Intent homepage = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(homepage);
                }

            }


        }
