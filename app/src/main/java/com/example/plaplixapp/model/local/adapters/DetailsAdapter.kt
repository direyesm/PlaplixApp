package com.example.plaplixapp.model.local.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plaplixapp.R
import com.example.plaplixapp.model.local.entities.PlapixdetallEnti

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>(){
    private var detailList = emptyList<PlapixdetallEnti>()

    fun updateListDetails(mDataList: List<PlapixdetallEnti>){
        detailList = mDataList
        notifyDataSetChanged()
    }


    inner class DetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_second, parent, false)
        return  DetailsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {

    }

    override fun getItemCount() = detailList.size
}