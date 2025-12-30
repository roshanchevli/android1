package com.example.firebasedemo

import java.io.Serializable

data class StudentModel(
    val id: String? = "",
    val name: String? = "",
    val rollno: String? = "",
    val email: String? = "",
    val phoneno: String? = ""
): Serializable
