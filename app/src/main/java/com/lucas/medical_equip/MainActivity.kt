package com.lucas.medical_equip

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
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
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMedicalTools()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        progressBar = findViewById(R.id.progresssBar11)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (menu != null) {
            searchView = menu.findItem(R.id.action_search).actionView as SearchView
        }
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                medicalAdapter.filter.filter(newText)
                return false
            }
        })

        return true
    }

    @SuppressLint("CheckResult")
    fun getMedicalTools() {
        //progressBar.
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