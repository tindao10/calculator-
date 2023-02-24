package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    List<String> list=new ArrayList<>();
    String history="";
    int size=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txtHistory=findViewById(R.id.txt_hisory);
        history = getIntent().getStringExtra("keyResult");
        txtHistory.setText(history);

        SharedPreferences mypref = getSharedPreferences("mysave", MODE_PRIVATE);
        history += "\n" + mypref.getString("ls", "");
        txtHistory.setText(history);
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences mypref=getSharedPreferences("mysave",MODE_PRIVATE);
        SharedPreferences.Editor myedit=mypref.edit();
        myedit.putString("ls",history);
        myedit.commit();

    }
}