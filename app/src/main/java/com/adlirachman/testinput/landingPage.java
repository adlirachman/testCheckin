package com.adlirachman.testinput;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class landingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    public void toVisPage (View view){
        Intent intent = new Intent (this, VisitorPage.class);
        startActivity(intent);
    }

    public void toEmpPage (View view){
        Intent intent = new Intent (this, EmployeePage.class);
        startActivity(intent);
    }

}
