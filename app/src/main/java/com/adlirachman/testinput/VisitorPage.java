package com.adlirachman.testinput;

import android.content.Intent;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VisitorPage extends AppCompatActivity {

    EditText edtNama,edtEmail,edtPhone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    Map<String, Visitor> visitors = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_page);

        edtNama = findViewById(R.id.inputNamaVis);
        edtEmail = findViewById(R.id.inputEmailVis);
        edtPhone = findViewById(R.id.inputPhoneVis);


    }

    public static class Visitor{

        private String Nama,Email,Phone,Date;

        public String getNama() {
            return Nama;
        }

        public void setNama(String nama) {
            Nama = nama;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }


        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public Visitor(String nama, String email, String phone) {
            Nama = nama;
            Email = email;
            Phone = phone;
        }

        public Visitor(String nama, String email, String phone, String date) {
            Nama = nama;
            Email = email;
            Phone = phone;
            Date = date;
        }
    }

    public void testDatabase(View view){
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Test Database");
    }


    public void simpan(View view){
        String NamaVis  = edtNama.getText().toString();
        String EmailVis = edtEmail.getText().toString();
        String PhoneVis = edtPhone.getText().toString();


        visitors.put(NamaVis, new Visitor(NamaVis,EmailVis,PhoneVis));
        myRef.setValue(visitors);
    }

}
