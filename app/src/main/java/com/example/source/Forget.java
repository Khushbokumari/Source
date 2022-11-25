package com.example.source;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Forget extends AppCompatActivity {

    //always declare here.....

    Button forgrtbtn;
    TextInputLayout username_var,newpassword_var,confirmpassword_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        forgrtbtn=findViewById(R.id.done);
        username_var=findViewById(R.id.username_text_field_design);
        newpassword_var=findViewById(R.id.Newpassword_text_field_design);
        confirmpassword_var=findViewById(R.id.confirmpassword_text_field_design);

        forgrtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        forgrtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_=username_var.getEditText().getText().toString();
                String newpassword_=newpassword_var.getEditText().getText().toString();
                String confirrmpassword_=confirmpassword_var.getEditText().getText().toString();
                if (!username_.isEmpty())
                {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if (!newpassword_.isEmpty())
                    {
                        newpassword_var.setError(null);
                        newpassword_var.setErrorEnabled(false);
                        if (!confirrmpassword_.isEmpty()){
                            confirmpassword_var.setError(null);
                            confirmpassword_var.setErrorEnabled(false);
                            Toast.makeText(getApplicationContext(), "Password change Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            confirmpassword_var.setError("password not match");
                        }
                    }
                    else {
                        newpassword_var.setError("enter new password");
                    }
                }else
                {
                    username_var.setError("Invalid");
                }
            }
        });


    }

}