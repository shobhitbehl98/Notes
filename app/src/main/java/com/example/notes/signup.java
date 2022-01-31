package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    private EditText msignupemail,msignuppassword;
    private RelativeLayout msignup;
    private TextView mgotologin;

    private FirebaseAuth firebaseAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        msignupemail=findViewById(R.id.signupemail);
        msignuppassword=findViewById(R.id.signuppassword);
        msignup=findViewById(R.id.signup);
        mgotologin=findViewById(R.id.textbacktologin);
        firebaseAuth=FirebaseAuth.getInstance();

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

                   firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                               sendEmailVerification();
                           }else{
                               Toast.makeText(getApplicationContext(),"Failed To Register",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });

               }
            }
        });


    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verification Email Sent, Verify and Log In Again",Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(signup.this,MainActivity.class));
                }
            });

        }else{
            Toast.makeText(getApplicationContext(),"Failed to Send Verification",Toast.LENGTH_SHORT).show();
        }
    }
}