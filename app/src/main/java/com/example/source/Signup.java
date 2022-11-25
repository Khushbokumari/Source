package com.example.source;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //always declare here.....

    TextInputLayout username_var,emailId_var,password_var,confirmpassword_var,mobileno_var;
    Button signupbtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupbtn=findViewById(R.id.signup);
        username_var=findViewById(R.id.username_text_field_design);
        emailId_var=findViewById(R.id.m_text_field_design);
        password_var=findViewById(R.id.password_text_field_design);
        confirmpassword_var=findViewById(R.id.confirmpassword_text_field_design);
        mobileno_var=findViewById(R.id.mobileno_text_field_design);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_ = username_var.getEditText().getText().toString();
                String emailId_ = emailId_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();
                String confirmpassword_ = confirmpassword_var.getEditText().getText().toString();
                String mobileno_ = mobileno_var.getEditText().getText().toString();

                if (!username_.isEmpty()) {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if (!emailId_.isEmpty()) {
                        emailId_var.setError(null);
                        emailId_var.setErrorEnabled(false);
                        
                        
                        if (!password_.isEmpty()) {
                            password_var.setError(null);
                            password_var.setErrorEnabled(false);



                            if (!confirmpassword_.isEmpty()){
                                confirmpassword_var.setError(null);
                                confirmpassword_var.setErrorEnabled(false);
                                if(!mobileno_.isEmpty()){
                                    mobileno_var.setError(null);
                                    mobileno_var.setErrorEnabled(false);
                                    if (!emailId_.matches("/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$/\n"))
                                    {
                                        firebaseDatabase=FirebaseDatabase.getInstance();
                                        reference=firebaseDatabase.getReference("datauser");
                                        String username_s = username_var.getEditText().getText().toString();
                                        String emailId_s = emailId_var.getEditText().getText().toString();
                                        String password_s = password_var.getEditText().getText().toString();
                                        String confirmpassword_s = confirmpassword_var.getEditText().getText().toString();
                                        String mobileno_s = mobileno_var.getEditText().getText().toString();
                                        storingdata storingdatas=new storingdata(username_s,emailId_s,password_s,confirmpassword_s,mobileno_s);
                                        reference.child(username_s).setValue(storingdatas);
                                        Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                                        startActivity(intent);
                                        finish();


                                    }
                                    else{
                                        emailId_var.setError("Invalid");
                                    }
                                    
                                }
                                else {
                                    mobileno_var.setError("Invalid mobile no");
                                }
                            }
                            else{
                                confirmpassword_var.setError("password not match");
                            }

                        } else {
                            emailId_var.setError("Invalid email");
                        }
                    } else {
                        password_var.setError("please enter the password");

                    }
                } else {
                    username_var.setError("please enter the user name");
                }
            }


                    




                


            
        });



    }








}