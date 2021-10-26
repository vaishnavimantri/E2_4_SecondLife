package com.example.secondlifetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.secondlifetesting.DBHelper;

public class LoginActivity extends AppCompatActivity {
    Button b1,b2;
    EditText ed1,ed2;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button)findViewById(R.id.login);
        ed1 = (EditText)findViewById(R.id.email);
        ed2 = (EditText)findViewById(R.id.Password);
        DB = new DBHelper(this);
    }

    public void Register(View view) {
        Intent registerpage = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerpage);
    }

    public void home(View view) {
        String Emailid = ed1.getText().toString();
        String password = ed2.getText().toString();

        if(Emailid.equals("")||password.equals(""))
            Toast.makeText(LoginActivity.this,"Please enter all fields", Toast.LENGTH_SHORT).show();
        else {

            Boolean emailpass = DB.checkemailpassword(Emailid, password);
            if (emailpass) {
                Toast.makeText(LoginActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                Intent homepage = new Intent(LoginActivity.this, NavigationbarActivity.class);
                startActivity(homepage);

            } else {
                Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
            }
        }

    }


}