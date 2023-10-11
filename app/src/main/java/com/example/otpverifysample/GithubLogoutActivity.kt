package com.example.otpverifysample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GithubLogoutActivity : AppCompatActivity() {

    var userName = ""

    private lateinit var githubUserName: TextView
    private lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_logout)

        githubUserName = findViewById(R.id.id)
        logoutBtn = findViewById(R.id.logOut)
        userName = intent.getStringExtra("githubUserName")!!
        githubUserName.text = userName

        logoutBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            this.startActivity(intent)
        }
    }
}