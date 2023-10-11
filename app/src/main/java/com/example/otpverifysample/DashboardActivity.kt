package com.example.otpverifysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardActivity : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val signOut = findViewById<Button>(R.id.button)
        val welcome = findViewById<TextView>(R.id.welcome)
        val name = findViewById<TextView>(R.id.name)

        signOut.setOnClickListener { signOut() }
        name.text = Firebase.auth.currentUser?.displayName.toString()
        welcome.text= Firebase.auth.currentUser?.email.toString()
    }

    private fun signOut() {
        // Sign out the user
        Firebase.auth.signOut()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("425458242811-qljrjldpt5bnolpklu53sd7eb7o1p7e9.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()
        finish()
    }

}