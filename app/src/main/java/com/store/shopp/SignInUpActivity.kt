package com.store.shopp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in_up.*
import kotlinx.android.synthetic.main.otp_layout.*

class SignInUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("SignInTest", "Success")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_up)
        val sharedPref = applicationContext.getSharedPreferences("phone_store", Context.MODE_PRIVATE)
        val sharedEditor = sharedPref.edit()
//        showPhoneNumberScreen()
        Next.setOnClickListener{
            if(phone_Number.text.toString().trim().length!=10){
                Toast.makeText(this, "Please Enter a Valid Phone Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            showOTPScreen()
        }
        submit_otp.setOnClickListener{
            if (otp.text.toString().trim().equals("1111")){
                sharedEditor.putString("currentUser",phone_Number.text.toString().trim())
                sharedEditor.apply()
                sharedEditor.commit()
                redirectToHome()
            }else{
                Toast.makeText(this,"Incorrect OTP", Toast.LENGTH_SHORT).show()
            }
        }
        change_number.setOnClickListener{
            showPhoneNumberScreen()
        }
        skip_signin.setOnClickListener{
            sharedEditor.putString("start_option","skip")
            sharedEditor.apply()
            sharedEditor.commit()
            redirectToHome()
        }

    }
    fun redirectToHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun showOTPScreen(){
        phone_number_screen.visibility = View.GONE
        otp_screen.visibility = View.VISIBLE
    }
    fun showPhoneNumberScreen(){
        phone_number_screen.visibility = View.VISIBLE
        otp_screen.visibility = View.GONE
    }
}