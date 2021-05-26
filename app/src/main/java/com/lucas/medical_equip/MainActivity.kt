package com.lucas.medical_equip

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucas.medical_equip.repository.MedicalTool
import com.lucas.medical_equip.service.RetrofitBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
class MainActivity : AppCompatActivity() {
    private lateinit var medicalTools: List<MedicalTool>
    private lateinit var medicalRecyclerView: RecyclerView
    private lateinit var medicalAdapter: MedicalToolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMedicalTools()
    }

    @SuppressLint("CheckResult")
    fun getMedicalTools() {
        val medTools = RetrofitBuilder.apiService.getMedicalEquipments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({toolsResponse ->
                    medicalTools = toolsResponse
                    medicalRecyclerView = findViewById(R.id.medicalToolRecyclerView)
                    medicalAdapter = MedicalToolAdapter(medicalTools)
                    medicalRecyclerView.adapter = medicalAdapter
                    //Set the layout managet to position the item
                    medicalRecyclerView.layoutManager = LinearLayoutManager(this)
                    logger.debug { toolsResponse }
                }, {error ->
                    logger.error { error }
                })
    }
}