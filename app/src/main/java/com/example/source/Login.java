package com.example.source;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    //always declare here.....

    TextInputLayout username_var,password_var;
    TextView signup,forget;

    Button Loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Loginbtn=findViewById(R.id.login);

        //define id

        forget=findViewById(R.id.forget);
        signup=findViewById(R.id.signup);

        username_var=findViewById(R.id.username_text_field_design);
        password_var=findViewById(R.id.password_text_field_design);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Forget.class);
                startActivity(intent);
            }
        });
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_=username_var.getEditText().getText().toString();
                String password_=password_var.getEditText().getText().toString();
                if(!username_.isEmpty())
                {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if(!password_.isEmpty())
                    {
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);
                        final String username_data=username_var.getEditText().getText().toString();
                        final String password_data=password_var.getEditText().getText().toString();
                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference=firebaseDatabase.getReference("datauser");
                        Query check_username=databaseReference.orderByChild("username").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);
                                    String passwordcheck=snapshot.child(username_data).child("password").getValue(String.class);
                                    if (passwordcheck.equals(password_data)){
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),Dashboard.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        password_var.setError("wrong password");
                                    }

                                }else
                                {
                                    username_var.setError("user does not exit");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else {
                        password_var.setError("please enter the password");
                    }


                }
                else{
                    username_var.setError("please enter the user name");
                }

            }
        });



    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login,menu);
        return true;

    }
    public void serve(View v)
    {
        Intent i=new Intent();
        i.setClass(this,Second.class);
        startActivity(i);
    }

    public void complain(View v)
    {
        Intent in=new Intent();
        in.setClass(this,Third.class);
        startActivity(in);
    }

    public void feed(View v)
    {
        Intent inn=new Intent();
        inn.setClass(this,Fourth.class);
        startActivity(inn);
    }*/


   /* public void signupbuttononclick(View view) {
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
    }*/
}