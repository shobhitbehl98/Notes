package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class forgotpassword extends AppCompatActivity {

    private EditText enterEmail;
    private RelativeLayout mpasswordRecoverbutton;
    private TextView mgobacktologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getSupportActionBar().hide();
        enterEmail=findViewById(R.id.typeEmail);
        mpasswordRecoverbutton=findViewById(R.id.recover);
        mgobacktologin=findViewById(R.id.textbacktologin);

        mgobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(forgotpassword.this,MainActivity.class);
                startActivity(it);
            }
        });

        mpasswordRecoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=enterEmail.getText().toString().trim();
                if(mail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Your mail",Toast.LENGTH_SHORT).show();
                }else{
                   // we have to send mail, after setting up firebase
                }
            }
        });


    }
}