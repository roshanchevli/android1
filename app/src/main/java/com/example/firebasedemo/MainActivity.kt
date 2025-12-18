package com.example.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.firebasedemo.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener((binding.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnlogout.setOnClickListener {
            AuthUI.getInstance().signOut(this)
                .addOnCompleteListener {
                    val intent= Intent(this, login::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show()
                }
        }
//
//      val currentUser= FirebaseAuth.getInstance().currentUser
//        if(currentUser == null){
//            val intent= Intent(this, login::class.java)
//            startActivity(intent)
//            finish()
//
//        }

//        if(currentUser!=null){
//            binding.tvname.text =  currentUser.displayName
//            binding.tvemail.text =currentUser.email
//
//            val photourl=currentUser.photoUrl
//
//            if(photourl!=null){
//                Glide.with(this).load(photourl).into(binding.profileImage);
//            }
//        }

        binding.btnlogout2.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference(ConstantData.MESSAGE_REFERENCE)
            ref.setValue("Hello World")
            Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
        }
    }
}














