package com.lucas.medical_equip.repository

import com.google.gson.annotations.SerializedName

data class MedicalTool (
    @SerializedName("toolID")
    val toolID: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("imageURL")
    val imageURL: String
)