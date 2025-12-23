package com.example.firebasedemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedemo.databinding.ActivityStudentDetailsBinding
import com.google.firebase.database.FirebaseDatabase

class StudentDetails : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnAddStudent.setOnClickListener {

            val ref= FirebaseDatabase.getInstance().getReference(ConstantData.STUDENT_REFERENCE)
            val student= StudentModel(
                binding.etStudentName.text.toString(),
                binding.etRollNo.text.toString(),
                binding.etEmail.text.toString(),
                binding.etMobileNo.text.toString()
            )
            ref.push().setValue(student)
                .addOnSuccessListener {
                    ConstantData().print("Student Added", this)
                }
                .addOnFailureListener {
                    ConstantData().print("Student Not Added", this)
                }
            binding.etStudentName.text.clear()
            binding.etRollNo.text.clear()
            binding.etEmail.text.clear()
            binding.etMobileNo.text.clear()
        }
    }
}