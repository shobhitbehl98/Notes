package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText mloginemail;
    private EditText mloginpassword;
    private RelativeLayout mlogin;
    private TextView mforgotpassword;
    private RelativeLayout mgotosignup;
    private FirebaseAuth firebaseAuth;
    private ProgressBar mprogressbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setStatusBarColor();
        mloginemail=findViewById(R.id.loginemail);
        mprogressbar=findViewById(R.id.progressbar);
        mloginpassword=findViewById(R.id.loginpassword);
        mlogin=findViewById(R.id.login);
        mforgotpassword=findViewById(R.id.forgotPassword);
        mgotosignup=findViewById(R.id.gotosignup);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(MainActivity.this,NotesActivity.class));
        }
        mgotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,signup.class));
            }
        });
        mforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,forgotpassword.class));
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mloginemail.getText().toString().trim();
                String password=mloginpassword.getText().toString().trim();
                if(email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"All Fields are required",Toast.LENGTH_SHORT).show();
                }else{
                    //Firebase
                    mprogressbar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Checking Verification",Toast.LENGTH_SHORT).show();
                                checkMailVerification();
                            }else{
                                mprogressbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"Account Does Not Exist",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

    }
        private void checkMailVerification(){
          FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
          if(firebaseUser.isEmailVerified()){
              Toast.makeText(getApplicationContext(),"LoggedIn",Toast.LENGTH_SHORT).show();
              finish();
              startActivity(new Intent(MainActivity.this,NotesActivity.class));
          }else{
              Toast.makeText(getApplicationContext(),"Email not verified",Toast.LENGTH_SHORT).show();
              mprogressbar.setVisibility(View.INVISIBLE);
              firebaseAuth.signOut();
          }
        }
        public void setStatusBarColor(){
            Window w = getWindow();
            w.setStatusBarColor(Color.parseColor("#F732AC"));
        }
}
