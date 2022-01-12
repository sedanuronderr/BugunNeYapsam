package com.example.bugunneyapsam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.bugunneyapsam.Kategoriİslemler.KategorilerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

     val timer =   object : CountDownTimer(1800, 3000) {
            override fun onFinish() {
                val intent = Intent(this@MainActivity, KategorilerActivity::class.java)
                startActivity(intent)

            }

            override fun onTick(p0: Long) {
                Log.d("SplashActivity", p0.toString())
            }
        }
        timer.start()
    }
}