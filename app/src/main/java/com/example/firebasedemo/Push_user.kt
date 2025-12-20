package com.example.firebasedemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedemo.databinding.ActivityMainBinding
import com.example.firebasedemo.databinding.ActivityPushUserBinding
import com.google.firebase.database.FirebaseDatabase

class Push_user : AppCompatActivity() {

    private lateinit var binding: ActivityPushUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnPush.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isEmpty()) {
                binding.etName.error = "Please enter name"
                return@setOnClickListener
            }
            val ref = FirebaseDatabase.getInstance().getReference(ConstantData.USERNAME_REFERENCE)
            ref.push().setValue(name)
                .addOnSuccessListener {
                    ConstantData().print("Data Added", this)
                }
                .addOnFailureListener {
                    ConstantData().print("Data Not Added", this)
                }
            binding.etName.text.clear()

        }
    }
}