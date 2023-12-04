package com.putragandad.urbanfarm.adapters.beranda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R

import com.putragandad.urbanfarm.models.beranda.ItemTanamanModels

class ItemTanamanRVAdapter(private val itemTanamanList : ArrayList<ItemTanamanModels>) :
    RecyclerView.Adapter<ItemTanamanRVAdapter.ItemTanamanViewHolder>() {

    var onItemClick : ((ItemTanamanModels) -> Unit)? = null

    class ItemTanamanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconTanaman: ImageView = itemView.findViewById(R.id.iv_icontanaman)
        val namaTanaman: TextView = itemView.findViewById(R.id.tv_icontanaman)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTanamanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tanamanlist_dashboard_rv, parent, false)
        return ItemTanamanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemTanamanList.size
    }

    override fun onBindViewHolder(holder: ItemTanamanViewHolder, position: Int) {
        val tanaman = itemTanamanList[position]

        holder.iconTanaman.setImageResource(tanaman.iconTanaman)
        holder.namaTanaman.text = tanaman.namaTanaman

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(tanaman)
        }
    }
}