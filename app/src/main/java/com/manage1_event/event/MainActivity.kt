package com.manage1_event.event

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.manage1_event.event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
         //to be done ,login activity

        binding.register.setOnClickListener {
            val intent= Intent(this,NavigationHostActivity::class.java)
            startActivity(intent)
        }

    }
}