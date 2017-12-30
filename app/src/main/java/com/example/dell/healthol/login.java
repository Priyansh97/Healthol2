package com.example.dell.healthol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void onButtonClick(View V)
    {
        EditText userid=findViewById(R.id.etuserid);
        EditText password=findViewById(R.id.etpwd);

        String useridstr=userid.getText().toString();
        String passwordstr=password.getText().toString();

        if(V.getId()==R.id.loginbtn)
        {

            String password1=helper.searchPass(useridstr);

            //check userid and password from database

            if(passwordstr.equals(password1))
            {
                //proceed further into the app
                Toast welcome=Toast.makeText(login.this,"Welcome to HealthOL",Toast.LENGTH_SHORT);
                welcome.show();
            }
            else
            {
                Toast msg3= Toast.makeText(login.this,"Incorrect Username or Password",Toast.LENGTH_SHORT);
                msg3.show();
            }


        }

        else if(V.getId()==R.id.sgnupbtn)
        {
            Intent intent=new Intent(login.this,signup.class);
            startActivity(intent);
        }
    }



}
