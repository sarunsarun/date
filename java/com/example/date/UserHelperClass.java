package com.example.date;

import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class UserHelperClass {
    String email,phoneNo ;
    private Object DatabaseReference;



    public UserHelperClass(String email,String phoneNo) {

        this.phoneNo = phoneNo;
        this.email = email;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}




    