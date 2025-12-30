package com.example.firebasedemo.adapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedemo.ConstantData
import com.example.firebasedemo.R
import com.example.firebasedemo.StudentModel
import com.example.firebasedemo.StudentUpdate
import com.google.firebase.database.FirebaseDatabase


//=================== write Adapter after colon======================
class StudentAdapter(
    private val studentList: ArrayList<StudentModel>
): RecyclerView.Adapter<StudentAdapter.StudentVH>(

) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentVH(view)
    }

    override fun onBindViewHolder(
        holder: StudentVH,
        position: Int
    ) {
        val studentModel = studentList[position]
        holder.tvname.text = studentModel.name
        holder.tvrollno.text = studentModel.rollno
        holder.tvemail.text = studentModel.email
        holder.tvphoneno.text = studentModel.phoneno

        val ref = FirebaseDatabase.getInstance().getReference(ConstantData.STUDENT_REFERENCE)

        holder.btnupdate.setOnClickListener {
            val intent= Intent(holder.itemView.context, StudentUpdate::class.java)
            intent.putExtra("student", studentModel)
            holder.itemView.context.startActivity(intent)
        }

        holder.btndelete.setOnClickListener {
            ref.child(studentModel.id!!).removeValue()
                .addOnSuccessListener {
                    val position = holder.adapterPosition
                    studentList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, studentList.size)
                    ConstantData().print("Student Deleted", holder.itemView.context)
                }
                .addOnFailureListener {
                    ConstantData().print("Student Not Deleted", holder.itemView.context)
                }
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
//========================== write ViewHolder after colon ==============================
    class StudentVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvname: TextView = itemView.findViewById(R.id.tvStudentName)
        var tvrollno: TextView = itemView.findViewById(R.id.tvRollNumber)
        var tvemail: TextView = itemView.findViewById(R.id.tvemail)
        var tvphoneno: TextView = itemView.findViewById(R.id.tvMobileNo)
        var btnupdate: Button = itemView.findViewById(R.id.btnUpdate)
        var btndelete: Button = itemView.findViewById(R.id.btnDelete)
    }
}