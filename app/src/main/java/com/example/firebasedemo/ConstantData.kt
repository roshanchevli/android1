package com.example.firebasedemo

import android.R
import android.content.Context
import android.widget.Toast

class ConstantData {
    companion object{
        const val MESSAGE_REFERENCE="message"
        const val USER_REFERENCE="user"
        const val USERNAME_REFERENCE="userName"
        const val STUDENT_REFERENCE="student"
    }
    fun print(text: String, context: Context){
        Toast.makeText(context, text, Toast .LENGTH_SHORT).show()
    }



}


