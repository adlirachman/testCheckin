package com.adlirachman.testinput;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VisitorPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_page);
        Intent intent = getIntent();


        TextView textView = findViewById(R.id.textView);
        textView.setText("Hello Visitor!");
    }

}
