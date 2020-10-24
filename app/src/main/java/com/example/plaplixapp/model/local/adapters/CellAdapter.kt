package com.example.plaplixapp.model.local.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plaplixapp.R
import com.example.plaplixapp.model.local.entities.PlapixEnti
import kotlinx.android.synthetic.main.cell_item_list.view.*

class CellAdapter(val mCellData: cellData): RecyclerView.Adapter<CellAdapter.CellViewHolder>(){
    private var cellList = emptyList<PlapixEnti>()

    fun updateAdapter(mList : List<PlapixEnti>){
        cellList = mList
        notifyDataSetChanged()
    }


    inner class CellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgCell = itemView.img
        val txtname = itemView.nametxt
        val txtprice = itemView.pricetxt
        val algo = itemView.setOnClickListener {
            mCellData.passTheCell(cellList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_item_list, parent, false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val cell = cellList[position]
        Glide.with(holder.itemView.context).load(cell.image).into(holder.imgCell)
        holder.txtname.text = cell.name
        holder.txtprice.text = "$" + cell.price.toString()
    }

    override fun getItemCount() = cellList.size

    interface cellData{
        fun passTheCell(cell : PlapixEnti)
    }
}