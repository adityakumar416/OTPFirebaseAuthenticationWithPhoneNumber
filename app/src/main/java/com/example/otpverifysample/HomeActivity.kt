package com.example.otpverifysample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.otpverifysample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
        private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.otpVerify.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        binding.button3.setOnClickListener {
            val intent = Intent(this,GoogleAuthenticationActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this,FacebookAuthenticationActivity::class.java)
            startActivity(intent)
        }
        binding.button4.setOnClickListener {
            val intent = Intent(this,GithubAuthActivity::class.java)
            startActivity(intent)
        }
    }
}