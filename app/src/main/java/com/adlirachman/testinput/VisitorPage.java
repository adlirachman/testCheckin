package com.adlirachman.testinput;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VisitorPage extends AppCompatActivity {

    EditText edtNama,edtEmail,edtPhone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    Map<String, Visitor> visitors = new HashMap<>();

    ArrayList<Visitor> list = new ArrayList<Visitor>();

    private ImageView imageView;
    private ValueEventListener postListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_page);

        edtNama = findViewById(R.id.inputNamaVis);
        edtEmail = findViewById(R.id.inputEmailVis);
        edtPhone = findViewById(R.id.inputPhoneVis);




    }

    public static class Visitor{

        private String Nama,Email,Phone;

        public String getNama() {
            return Nama;
        }

        public void setNama(String nama) {
            this.Nama = nama;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            this.Email = email;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            this.Phone = phone;
        }

        public Visitor() {

        }

        public Visitor(String nama, String email, String phone) {
            this.Nama = nama;
            this.Email = email;
            this.Phone = phone;
        }

    }

    public void testDatabase(View view){
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Test Database");
    }

    //Storing data to Firebase
    public void simpan(View view){
        String NamaVis  = edtNama.getText().toString();
        String EmailVis = edtEmail.getText().toString();
        String PhoneVis = edtPhone.getText().toString();


        DatabaseReference myRef = database.getReference("visitors");
        String key = myRef.push().getKey();
        visitors.put(key, new Visitor(NamaVis,EmailVis,PhoneVis));
        myRef.setValue(visitors);
    }

    //Reading data from Firebase
    DatabaseReference ref1 = database.getReference("visitors");
    public void readFromFireBase(View view){
       ref1.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot message: dataSnapshot.getChildren()){
                   Visitor visitor = dataSnapshot.getValue(Visitor.class);
                   tampilUser(visitor);
               }



           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               System.out.println("The read failed: " + databaseError.getMessage());

           }
       });


    }

    public void tampilUser(Visitor visitor){
        TextView tampilNama = (TextView) findViewById(R.id.outNama);
        TextView tampilEmail = (TextView) findViewById(R.id.outEmail);
        TextView tampilPhone = (TextView) findViewById(R.id.outPhone);

        tampilNama.setText(visitor.getNama());
        tampilEmail.setText(visitor.getEmail());
        tampilPhone.setText(visitor.getPhone());
    }



}
