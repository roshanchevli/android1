package com.example.firebasedemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasedemo.adapter.StudentAdapter
import com.example.firebasedemo.databinding.ActivityStudentShowBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Student_show_activity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentShowBinding
    private lateinit var studList: ArrayList<StudentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStudentShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        ====================== bind adapter ===================================
       studList=ArrayList<StudentModel>()
        val adapter = StudentAdapter(studList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter


        val ref= FirebaseDatabase.getInstance().getReference(ConstantData.STUDENT_REFERENCE)
        ref.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val student = data.getValue(StudentModel::class.java)
                    if (student != null) {
                        studList.add(student)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                ConstantData().print(error.message, this@Student_show_activity)
            }
        })
    }
}