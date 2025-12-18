package com.example.firebasedemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedemo.databinding.ActivityMainBinding
import com.example.firebasedemo.databinding.ActivityUserAddBinding
import com.google.firebase.database.FirebaseDatabase

class UserAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnAddUser.setOnClickListener {
            val ref= FirebaseDatabase.getInstance().getReference(ConstantData.USER_REFERENCE)
            val user= UserModel(
                name=binding.enterUsername.text.toString(),
                email=binding.enterEmail.text.toString(),
                password=binding.enterPass.text.toString(),
                mobileno=binding.enterMobilNo.text.toString()
            )

            ref.child(binding.enterID.text.toString()).setValue(user)
                .addOnSuccessListener {
                    ConstantData().print("Data Added", this)
                }
                .addOnFailureListener {
                    ConstantData().print("Data Not Added", this)
                }
        }

    }
}