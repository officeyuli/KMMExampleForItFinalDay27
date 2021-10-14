package com.officeyuli.kmmexampleforitfinal.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.officeyuli.kmmexampleforitfinal.android.view.CafeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private val adapter: CafeAdapter by lazy{ CafeAdapter() }
    private lateinit var cafeRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cafeRecyclerView = findViewById(R.id.rv_cafeList)
        cafeRecyclerView.adapter = adapter
        cafeRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        cafeRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.fetchCafeList("taipei")//進行網路請求
        viewModel.fetchCafeFromDB()//撈取DB資料
        viewModel.cafeListLiveData.observe(this, Observer {
            adapter.cafeList = it//這邊的cafeList已經變成ＤＢ的CAFE了
            adapter.notifyDataSetChanged()
        })

    }
}
