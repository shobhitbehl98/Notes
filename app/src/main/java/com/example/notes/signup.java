package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    private EditText msignupemail,msignuppassword;
    private RelativeLayout msignup;
    private TextView mgotologin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        msignupemail=findViewById(R.id.signupemail);
        msignuppassword=findViewById(R.id.signuppassword);
        msignup=findViewById(R.id.signup);
        mgotologin=findViewById(R.id.textbacktologin);

        mgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(signup.this,MainActivity.class);
                startActivity(it);
            }
        });

        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String mail=msignupemail.getText().toString().trim();
               String password=msignuppassword.getText().toString().trim();
               if(mail.isEmpty()||password.isEmpty()){
                   Toast.makeText(getApplicationContext(),"All Fields are required",Toast.LENGTH_SHORT).show();
               }else if(password.length()<7){
                   Toast.makeText(getApplicationContext(),"Password should be greater than 7 characters",Toast.LENGTH_SHORT).show();

               }else{
                   //Firebase
               }
            }
        });


    }
}