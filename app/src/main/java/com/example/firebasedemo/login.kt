package com.example.firebasedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firebase.ui.auth.AuthUI
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val provider= arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build(),
                                    AuthUI.IdpConfig.PhoneBuilder().build(),
                                    AuthUI.IdpConfig.EmailBuilder().build())

        val signinIntent=AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(provider)
            .build()

        startActivityForResult(signinIntent,100)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (resultCode == RESULT_OK) {
                if (currentUser != null) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }
            }
        }
    }
}