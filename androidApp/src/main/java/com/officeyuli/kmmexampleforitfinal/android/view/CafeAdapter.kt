package com.officeyuli.kmmexampleforitfinal.android.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.officeyuli.kmmexampleforitfinal.android.R
import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem

class CafeAdapter : RecyclerView.Adapter<CafeViewHolder>() {
    var cafeList = listOf<CafeResponseItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cafe, parent, false)

        return CafeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        val cafe: CafeResponseItem = cafeList[position]
        holder.bind(cafe)
    }

    override fun getItemCount() = cafeList.size
}