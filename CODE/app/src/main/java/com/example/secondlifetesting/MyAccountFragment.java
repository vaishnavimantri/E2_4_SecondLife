package com.example.secondlifetesting;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.secondlifetesting.DBHelper;
import com.example.secondlifetesting.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;
import androidx.fragment.app.Fragment;


public class MyAccountFragment extends Fragment {

    private TextView emailtext, phonetext;
    String emailid1, phone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_my_account, container, false);

        return view;
    }



}