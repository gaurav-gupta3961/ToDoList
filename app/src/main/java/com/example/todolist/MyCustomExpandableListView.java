package com.example.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MyCustomExpandableListView extends BaseExpandableListAdapter
{
    Activity context;
    ArrayList<String> titles;
    Map<String,String> descriptions;

    public MyCustomExpandableListView(Activity context, ArrayList<String> titles, Map<String, String> descriptions) {
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return titles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return descriptions.get(titles.get(i));
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final int x=i;
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=layoutInflater.inflate(R.layout.list_group,null,false);
        TextView t=(TextView)rowview.findViewById(R.id.textViewgroup);
        t.setText((String)getGroup(i));
        ImageView imageView  =(ImageView)rowview.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setTitle("Really").setMessage("Are you want to delete").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sp =context.getSharedPreferences("com.example.todolist", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor =sp.edit();
                        editor.remove((String)getGroup(x)).apply();
                        Intent intent = new Intent(context,MainActivity.class);
                        context.startActivity(intent);
                    }
                }).setNegativeButton("Cancel",null).setCancelable(false);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                /*SharedPreferences sp =context.getSharedPreferences("com.example.todolist", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor =sp.edit();
                editor.remove((String)getGroup(x)).apply();
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);*/
            }
        });
        return rowview;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=layoutInflater.inflate(R.layout.list_child,null,false);
        TextView t=(TextView)rowview.findViewById(R.id.textViewchild);
        t.setText(descriptions.get(titles.get(i)));
        return rowview;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;

    }
}
