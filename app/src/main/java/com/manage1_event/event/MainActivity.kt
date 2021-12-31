package com.manage1_event.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manage1_event.event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var _binding : ActivityMainBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}