package com.example.bugunneyapsam.Kategoriİslemler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.bugunneyapsam.Class.Kategori
import com.example.bugunneyapsam.R
import dagger.hilt.android.AndroidEntryPoint


class KategorilerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategoriler)
        supportActionBar?.hide()


    }
}