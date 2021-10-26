package com.example.secondlifetesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemsActivity extends AppCompatActivity {
    EditText itemname,description,SoR,PNO;
    Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);



    }
    public void process(View view) {
        itemname = (EditText) findViewById(R.id.itemName);
        category = findViewById(R.id.categories);
        description = (EditText) findViewById(R.id.Description);
        SoR = (EditText) findViewById(R.id.RorS);
        PNO = (EditText) findViewById(R.id.phone);


        String iname = itemname.getText().toString().trim();
        String cat = category.getSelectedItem().toString().trim();
        String des = description.getText().toString().trim();
        String sore = SoR.getText().toString().trim();
        String pno = PNO.getText().toString().trim();

        if (iname.equals("") || des.equals("") || pno.equals("") || sore.equals("")) {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        } else {
            DataHolder obj = new DataHolder(cat,des,sore,pno);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference node = db.getReference("Items");
            node.child(iname).setValue(obj);

            Toast.makeText(getApplicationContext(), "Item Details stored sucessfully", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(AddItemsActivity.this, ItemDetailsActivity.class);
            startActivity(intent);


        }

    }
    }

