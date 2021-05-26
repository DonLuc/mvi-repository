package com.lucas.medical_equip

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.medical_equip.service.RetrofitBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
class MainActivity : AppCompatActivity() {
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
                    logger.debug { toolsResponse }
                }, {error ->
                    logger.error { error }
                })
    }
}