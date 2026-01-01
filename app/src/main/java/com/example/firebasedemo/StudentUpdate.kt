package com.example.firebasedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasedemo.databinding.ActivityStudentUpdateBinding
import com.example.firebasedemo.databinding.ActivityUserAddBinding
import com.google.firebase.database.FirebaseDatabase

class StudentUpdate : AppCompatActivity() {
    private lateinit var binding: ActivityStudentUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val student = intent.getSerializableExtra("student") as StudentModel
        binding.etRollNo.setText(student.rollno)
        binding.etName.setText(student.name)
        binding.etEmail.setText(student.email)
        binding.etMobile.setText(student.phoneno)
        binding.btnUpdate.setOnClickListener {

            val ref= FirebaseDatabase.getInstance().getReference(ConstantData.STUDENT_REFERENCE)
            val student= StudentModel(
                id=student.id,
                name=binding.etName.text.toString(),
                rollno=binding.etRollNo.text.toString(),
                email=binding.etEmail.text.toString(),
                phoneno=binding.etMobile.text.toString()
            )

            ref.child(student.id!!).setValue(student)
                .addOnSuccessListener {
                    ConstantData().print("Data Updated", this)
                    startActivity(Intent(this, Student_show_activity::class.java))
                }
                .addOnFailureListener {
                    ConstantData().print("Data Not Updated", this)
                }
        }
    }
}