package com.officeyuli.kmmexampleforitfinal.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.officeyuli.kmmexampleforitfinal.Greeting
import com.officeyuli.kmmexampleforitfinal.android.view.CafeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private val adapter: CafeAdapter by lazy{ CafeAdapter() }
    private lateinit var cafeRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchCafeList()
        cafeRecyclerView = findViewById(R.id.rv_cafeList)
        cafeRecyclerView.adapter = adapter
        cafeRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        cafeRecyclerView.addItemDecoration(
            DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
        )
        viewModel.cafeListLiveData.observe(this,{
            Log.e("TAG", "onCreate: "+it )
            adapter.cafeList = it
            adapter.notifyDataSetChanged()
        })

    }
}
