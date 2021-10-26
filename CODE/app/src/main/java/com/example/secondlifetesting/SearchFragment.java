package com.example.secondlifetesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SearchFragment extends Fragment {

    FloatingActionButton additems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Button recycler = (Button) view.findViewById(R.id.recylerview);

        additems = view.findViewById(R.id.fab);
        additems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent additempage = new Intent(getActivity(), AddItemsActivity.class);
                getContext().startActivity(additempage);
            }
        });
        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recyclerview = new Intent(getActivity(), RecylerActivity.class);
                getContext().startActivity(recyclerview);
            }
        });

        return view;
    }
}