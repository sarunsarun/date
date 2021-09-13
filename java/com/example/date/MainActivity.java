package com.example.date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    TextInputLayout t1,t2 ,regPhoneNo;
    ProgressBar bar ;
    Button regBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        t1=(TextInputLayout)findViewById(R.id.email);
        regPhoneNo=(TextInputLayout)findViewById(R.id.reg_phoneNo);
        t2=(TextInputLayout)findViewById(R.id.pwd);
        bar=(ProgressBar) findViewById(R.id.progressBar);
        regBtn = findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String email = t1.getEditText().getText().toString();
                String reg_phoneNo = regPhoneNo.getEditText().getText().toString();
                UserHelperClass helperClass = new UserHelperClass(email, reg_phoneNo);
                reference.child(reg_phoneNo).setValue(helperClass);
//Get all the values
            }



            public void signuphere(View view) {
                bar.setVisibility(View.VISIBLE);
                String email;
                email = Objects.requireNonNull(t1.getEditText()).getText().toString();
                String password = Objects.requireNonNull(t2.getEditText()).getText().toString();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, (OnCompleteListener<AuthResult>) task -> {
                            if (task.isSuccessful()) {
                                bar.setVisibility(View.INVISIBLE);
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                            } else {
                                bar.setVisibility(View.INVISIBLE);
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(getApplicationContext(), "Process Error", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });        }

    public void gotosignin(View view) {
        startActivity(new Intent(MainActivity.this, login.class));
    }
}