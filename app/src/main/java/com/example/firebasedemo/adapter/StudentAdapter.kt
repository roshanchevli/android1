package com.example.firebasedemo.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedemo.R
import com.example.firebasedemo.StudentModel


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

        holder.btnupdate.setOnClickListener {

        }

        holder.btndelete.setOnClickListener {

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
        var btnupdate: Button=itemView.findViewById(R.id.btnUpdate)
        var btndelete: Button=itemView.findViewById(R.id.btnDelete)
    }
}