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

      val currentUser= FirebaseAuth.getInstance().currentUser
        if(currentUser == null){
            val intent= Intent(this, login::class.java)
            startActivity(intent)
            finish()

        }

        if(currentUser!=null){
            binding.tvname.text =  currentUser.displayName
            binding.tvemail.text =currentUser.email

            val photourl=currentUser.photoUrl

            if(photourl!=null){
                Glide.with(this).load(photourl).into(binding.profileImage);
            }
        }

        binding.btnlogout2.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference(ConstantData.MESSAGE_REFERENCE)
            ref.setValue("Hello World")
            Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
        }

        binding.btnlogout3.setOnClickListener {
            val ref= FirebaseDatabase.getInstance().getReference(ConstantData.MESSAGE_REFERENCE)
            ref.child("user").setValue("roshan")
            ConstantData().print("Data Added", this)

        }


        binding.btnlogout4.setOnClickListener {
//            val ref= FirebaseDatabase.getInstance().getReference(ConstantData.USER_REFERENCE)
//            val user= UserModel(
//                name="roshan",
//                email="james.c.mcreynolds@example-pet-store.com",
//                password="123",
//                mobileno="1234567890"
//            )
//
//            ref.child("user1").setValue(user)
//                .addOnSuccessListener {
//                    ConstantData().print("Data Added", this)
//                }
//                .addOnFailureListener {
//                    ConstantData().print("Data Not Added", this)
//                }

            val intent= Intent(this, UserAddActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnlogout5.setOnClickListener {
            val intent= Intent(this, Push_user::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnlogout6.setOnClickListener {
            val intent= Intent(this, StudentDetails::class.java)
            startActivity(intent)
            finish()
        }
    }
}














