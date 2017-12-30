package com.example.dell.healthol;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }

    public void onButtonClick(View V)
        {
        if(V.getId()==R.id.submitbtn) {
            int res=0;

            EditText uname = findViewById(R.id.etname);
            EditText uemailid = findViewById(R.id.etid);
            EditText upassw = findViewById(R.id.etpwd);
            EditText ucpassw = findViewById(R.id.etcpwd);
            EditText uphone = findViewById(R.id.etphn);
            EditText uaddress = findViewById(R.id.etadd);
            EditText udob = findViewById(R.id.etdob);

            String namestr = uname.getText().toString();
            String emailstr = uemailid.getText().toString();
            String pwdstr = upassw.getText().toString();
            String cpwdstr = ucpassw.getText().toString();

            String phnstr = uphone.getText().toString();
            String addstr = uaddress.getText().toString();
            String dobstr = udob.getText().toString();


                if ((!pwdstr.equals(cpwdstr))||namestr.isEmpty()||emailstr.isEmpty()||pwdstr.isEmpty()||cpwdstr.isEmpty()||phnstr.isEmpty()||addstr.isEmpty()||dobstr.isEmpty()) {
                    if (!pwdstr.equals(cpwdstr)) {
                        //popup
                        Toast msg = Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_SHORT);
                        msg.show();
                        res = 0;
                    } else {
                        Toast msg2 = Toast.makeText(this, "All Fields required!!", Toast.LENGTH_SHORT);
                        msg2.show();
                        res = 0;
                    }
                }
                else res=1;

            if (res == 1) {
                //upload data to database
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setPassword(pwdstr);
                c.setPhone(phnstr);
                c.setAddress(addstr);
                c.setDob(dobstr);

                helper.insertContact(c);
                Toast reg = Toast.makeText(signup.this, "Successfully Registered", Toast.LENGTH_SHORT);
                reg.show();
            }
        }


        else if(V.getId()==R.id.clrbtn)
        {
            ((EditText)findViewById(R.id.etname)).setText("");
            ((EditText)findViewById(R.id.etpwd)).setText("");
            ((EditText)findViewById(R.id.etcpwd)).setText("");
            ((EditText)findViewById(R.id.etid)).setText("");
            ((EditText)findViewById(R.id.etphn)).setText("");
            ((EditText)findViewById(R.id.etadd)).setText("");
            ((EditText)findViewById(R.id.etdob)).setText("");

        }

        else if(V.getId()==R.id.tvback)
        {
            Intent intent=new Intent(signup.this,login.class);
            startActivity(intent);

        }

        }


    }

