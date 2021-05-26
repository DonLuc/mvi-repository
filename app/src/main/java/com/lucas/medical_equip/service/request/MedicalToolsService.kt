package com.lucas.medical_equip.service.request

import com.lucas.medical_equip.repository.MedicalTool
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface MedicalToolsService {
    @Headers("Content-Type: application/json")
    @GET("/api/mobiletools/tools")
    fun getMedicalEquipments(): Observable<MedicalTool>
}