package com.store.shopp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        runTimer()
    }
    private fun runTimer(){
        val timer = object: CountDownTimer(5000,1000){
            override fun onFinish() {
                val sharedPref = applicationContext.getSharedPreferences("phone_Store", Context.MODE_PRIVATE)
                if (sharedPref.contains("start_option") && sharedPref.getString("start_option","")=="skip"){
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    return
                }
                if (sharedPref.contains("currentUser")){
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    return
                } else {
                    val intent = Intent(this@SplashActivity, SignInUpActivity::class.java)
                    startActivity(intent)
                    finish()
                }
//                    val intent = Intent(this@SplashActivity, SignInUpActivity::class.java)
//                   startActivity(intent)
            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }
        timer.start()
    }
}