package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTask extends AppCompatActivity {
   EditText title;
   EditText description;
   SharedPreferences sp;
   SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        title=(EditText)findViewById(R.id.editText);
        description =(EditText)findViewById(R.id.editText2);

    }
    public void save(View view)
    {
        sp=getSharedPreferences("com.example.todolist",MODE_PRIVATE);
         editor=sp.edit();
        editor.putString(title.getText().toString(),description.getText().toString()).apply();
        Intent intent = new Intent(AddTask.this,MainActivity.class);
        startActivity(intent);

    }

   public void discard(View view)
   {
       Intent intent = new Intent(AddTask.this,MainActivity.class);
       startActivity(intent);
   }
}
