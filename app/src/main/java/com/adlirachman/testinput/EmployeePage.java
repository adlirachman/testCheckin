package com.adlirachman.testinput;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.mail.Message;

public class EmployeePage extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);
        Button addImage = (Button) findViewById(R.id.btnEmail);
        addImage.setOnClickListener(this);
//        addImage.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                new SendMail().execute("");
//            }
//        });
    }

    private void sendEmail() {
        //Getting content for email
        String email = "adli.rahman23@gmail.com";
        String subject = "Testing";
        String message = "Welcome Test!";

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }{
        sendEmail();
    }




}


