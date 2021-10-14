package com.officeyuli.kmmexampleforitfinal.android.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.officeyuli.kmmexampleforitfinal.CAFE
import com.officeyuli.kmmexampleforitfinal.android.R

class CafeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.tv_cafename)
    private val address: TextView = itemView.findViewById(R.id.tv_address)

    fun bind(cafe: CAFE) {
        this.name.text = cafe.name
        this.address.text = cafe.address
    }
}