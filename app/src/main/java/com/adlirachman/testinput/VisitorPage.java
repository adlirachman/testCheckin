package com.adlirachman.testinput;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.provider.MediaStore;
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
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    ArrayList<Visitor> list = new ArrayList<Visitor>();

    private ImageView imageView;
    private ValueEventListener postListener;

    static final int REQUEST_IMAGE_CAPTURE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_page);

        edtNama = findViewById(R.id.inputNamaVis);
        edtEmail = findViewById(R.id.inputEmailVis);
        edtPhone = findViewById(R.id.inputPhoneVis);




    }

    public static class Visitor{

        private String Nama,Email,Phone,Checkin,Checkout;

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

        public String getCheckin() {
            return Checkin;
        }

        public void setCheckin(String checkin) {
            Checkin = checkin;
        }

        public String getCheckout() {
            return Checkout;
        }

        public void setCheckout(String checkout) {
            Checkout = checkout;
        }

        public Visitor() {

        }

        public Visitor(String nama, String email, String phone, String checkin, String checkout) {
            this.Nama = nama;
            this.Email = email;
            this.Phone = phone;
            this.Checkin = checkin;
            this.Checkout = checkout;
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


        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        date = dateFormat.format(calendar.getTime());
        SimpleDateFormat jamFormat = new SimpleDateFormat("HH:mm:ss");
        String jamCheckin = jamFormat.format(calendar.getTime());

        DatabaseReference myRef = database.getReference("visitors/"+date);
        String key = myRef.push().getKey();
        visitors.put(EmailVis, new Visitor(NamaVis,EmailVis,PhoneVis,jamCheckin,null));
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
//                   tampilUser(visitor);
               }



           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               System.out.println("The read failed: " + databaseError.getMessage());

           }
       });


    }

//    public void tampilUser(Visitor visitor){
//        TextView tampilNama = (TextView) findViewById(R.id.outNama);
//        TextView tampilEmail = (TextView) findViewById(R.id.outEmail);
//        TextView tampilPhone = (TextView) findViewById(R.id.outPhone);
//
//        tampilNama.setText(visitor.getNama());
//        tampilEmail.setText(visitor.getEmail());
//        tampilPhone.setText(visitor.getPhone());
//    }

    public void dispatchTakePictureIntent(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView mImageView = (ImageView) findViewById(R.id.mImageView);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }


}
