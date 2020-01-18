package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Destroyable;

public class MainActivity extends AppCompatActivity {
Button addNewtask;
ExpandableListView list;
ArrayList<String> titles;
Map<String,String> descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list =(ExpandableListView)findViewById(R.id.exListView);
        fillData();
        MyCustomExpandableListView myCustomExpandableListView = new MyCustomExpandableListView(this,titles,descriptions);
        list.setAdapter(myCustomExpandableListView);


    }
    public void add(View view)
    {
        Intent intent = new Intent(MainActivity.this,AddTask.class);
        startActivity(intent);
    }
    public void fillData()
    {
        titles= new ArrayList<>();
        descriptions = new HashMap<String,String>();
        SharedPreferences sp = getSharedPreferences("com.example.todolist",MODE_PRIVATE);
       // SharedPreferences.Editor editor = sp.edit();
       descriptions= (Map<String,String>) sp.getAll();
        for (Map.Entry<String, ?> entry : descriptions.entrySet()) {
            Log.i("map values", entry.getKey() + ": " + entry.getValue().toString());}
        for ( String key : descriptions.keySet() )
        {
            titles.add(key);
        }

    }

}
