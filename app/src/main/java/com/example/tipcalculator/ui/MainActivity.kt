package com.example.tipcalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.R
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }
}