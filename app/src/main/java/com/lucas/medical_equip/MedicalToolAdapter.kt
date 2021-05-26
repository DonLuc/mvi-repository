package com.lucas.medical_equip

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucas.medical_equip.repository.MedicalTool

class MedicalToolAdapter(private val medicalTools: List<MedicalTool>) : RecyclerView.Adapter<MedicalToolAdapter.ViewHolder>() {

    inner class ViewHolder(medicalToolItemView: View) : RecyclerView.ViewHolder(medicalToolItemView) {
        val medicalToolImage = itemView.findViewById<ImageView>(R.id.medical_tool_image)
        val medicalToolDescription = itemView.findViewById<TextView>(R.id.medical_tool_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        //Inflate the view item
        val medicalToolView = inflater.inflate(R.layout.medical_tool_item, parent, false)
        return ViewHolder(medicalToolView)
    }

    override fun getItemCount(): Int {
        return medicalTools.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get data model based on position
        val medicalTool: MedicalTool = medicalTools.get(position)

        //Set the items views based on your views data model
        val medicalToolImage = holder.medicalToolImage
        val medicalToolDescription = holder.medicalToolDescription
        medicalToolImage.setImageURI(Uri.parse(medicalTool.imageURL))
        medicalToolDescription.setText(medicalTool.description)
    }
}
