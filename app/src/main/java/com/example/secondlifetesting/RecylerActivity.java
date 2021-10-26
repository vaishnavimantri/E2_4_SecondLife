package com.example.secondlifetesting;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecylerActivity extends AppCompatActivity {

    ImageView img;
    RecyclerView recyclerView;

    String s1[], s2[], s3[];
    int images[] ={R.drawable.book,R.drawable.car,R.drawable.chair,R.drawable.laptop1,R.drawable.mobile};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler);



        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.Items);
        s2 = getResources().getStringArray(R.array.description);
        s3 = getResources().getStringArray(R.array.price);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,s3,images );
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}