package com.lucas.medical_equip

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucas.medical_equip.repository.MedicalTool
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class MedicalToolAdapter(private var medicalTools: List<MedicalTool>) : RecyclerView.Adapter<MedicalToolAdapter.ViewHolder>(), Filterable {
    var medicalToolsFiltered = ArrayList<MedicalTool>()

    init {
        medicalToolsFiltered = medicalTools as ArrayList<MedicalTool>
    }
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
        return medicalToolsFiltered.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get data model based on position
        val medicalTool: MedicalTool = medicalToolsFiltered.get(position)

        //Set the items views based on your views data model
        val medicalToolImage = holder.medicalToolImage
        val medicalToolDescription = holder.medicalToolDescription
        //medicalToolImage.setImageURI(Uri.parse(medicalTool.imageURL))
        Picasso.get().load(medicalTool.imageURL).into(medicalToolImage)
        medicalToolDescription.setText(medicalTool.description)
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if(charString.isEmpty()) {
                    medicalToolsFiltered = medicalTools as ArrayList<MedicalTool>
                } else {
                    val resultList = ArrayList<MedicalTool>()
                    for(medicalItem in medicalTools) {
                        if(medicalItem.description.toLowerCase(Locale.ROOT).contains(charString.toLowerCase(Locale.ROOT))) {
                            resultList.add(medicalItem)
                        }
                    }
                    medicalToolsFiltered = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = medicalToolsFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                medicalToolsFiltered = results?.values as ArrayList<MedicalTool>
                notifyDataSetChanged()
            }
        }
    }
}
