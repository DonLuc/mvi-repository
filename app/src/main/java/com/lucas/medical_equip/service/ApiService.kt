package com.lucas.medical_equip.service

import com.lucas.medical_equip.repository.MedicalTool
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIService {
    @Headers("Content-Type: application/json")
    @GET("/api/mobiletools/tools")
    fun getMedicalEquipments(): Call<MedicalTool>
}