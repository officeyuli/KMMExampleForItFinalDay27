package com.officeyuli.kmmexampleforitfinal.android.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.officeyuli.kmmexampleforitfinal.android.R
import com.officeyuli.kmmexampleforitfinal.ktor.response.CafeResponseItem

class CafeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.tv_cafename)
    private val address: TextView = itemView.findViewById(R.id.tv_address)

    fun bind(cafe: CafeResponseItem) {
        this.name.text = cafe.name
        this.address.text = cafe.address
    }
}