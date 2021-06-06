package com.example.online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpVerification extends AppCompatActivity {

    EditText text1,text2,text3,text4,text5,text6;
    Button verifyotp;
    String backendotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        text1=findViewById(R.id.otpnum1);
        text2=findViewById(R.id.otpnum2);
        text3=findViewById(R.id.otpnum3);
        text4=findViewById(R.id.otpnum4);
        text5=findViewById(R.id.otpnum5);
        text6=findViewById(R.id.otpnum6);

    //    EditText[] otp={text1,text2,text3,text4,text5,text6};

        text1.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (text1.getText().length() == 1)
                    text2.requestFocus();
                return false;
            }
        });
        text2.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (text2.getText().length() == 1)
                    text3.requestFocus();
                return false;
            }
        });
        text3.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (text3.getText().length() == 1)
                    text4.requestFocus();
                return false;
            }
        });
        text4.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (text4.getText().length() == 1)
                    text5.requestFocus();
                return false;
            }
        });

        text5.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (text5.getText().length() == 1)
                    text6.requestFocus();
                return false;
            }
        });

        verifyotp=findViewById(R.id.btn_verifyotp);
        ProgressBar progressBar=findViewById(R.id.verify_btn_progressbar);

        TextView mobileno=findViewById(R.id.otp_verification_mobile);

        mobileno.setText(String.format("+91%s", getIntent().getStringExtra("Mobileno")));

        backendotp=getIntent().getStringExtra("backendotp");

//        String textotp1=text1.getText().toString().trim();
//        String textotp2=text2.getText().toString().trim();
//        String textotp3=text3.getText().toString().trim();
//        String textotp4=text4.getText().toString().trim();
//        String textotp5=text5.getText().toString().trim();
//        String textotp6=text6.getText().toString().trim();


        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //&& !textotp2.isEmpty()&&!textotp3.isEmpty()&&!textotp4.isEmpty()&&!textotp5.isEmpty()&&!textotp6.isEmpty()
                if(!text1.getText().toString().trim().isEmpty() ){

                    String EnteredOtp=text1.getText().toString()+text2.getText().toString()
                                        +text3.getText().toString()+text4.getText().toString()
                                        +text5.getText().toString()+text6.getText().toString();

                    if (backendotp!=null){

                        progressBar.setVisibility(View.VISIBLE);
                        verifyotp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                backendotp,EnteredOtp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Intent intent=new Intent(OtpVerification.this,MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(OtpVerification.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                    else {
                        Toast.makeText(OtpVerification.this, "Pleace cheak internet Conncetion", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(OtpVerification.this, "Please Enter 6 digit otp number", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}