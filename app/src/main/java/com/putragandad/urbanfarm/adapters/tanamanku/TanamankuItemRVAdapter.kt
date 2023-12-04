package com.putragandad.urbanfarm.adapters.tanamanku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels

class TanamankuItemRVAdapter(
    val context: Context,
    val tanamankuClickDeleteInterface: TanamankuClickDeleteInterface,
    val tanamankuClickInterface: TanamankuClickInterface
) :
    RecyclerView.Adapter<TanamankuItemRVAdapter.ViewHolder>()
{
    private val allTanaman = ArrayList<TanamankuItemModels>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tanamankuTitleTV = itemView.findViewById<TextView>(R.id.tv_title_card_tanamanku_rv)
        val tanamankuTimestampTV = itemView.findViewById<TextView>(R.id.tv_timestamp_card_tanamanku_rv)
        val tanamankuFotoIV = itemView.findViewById<ImageView>(R.id.iv_card_tanamanku_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_tanamanku_rv,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allTanaman.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tanamankuTitleTV.setText(allTanaman.get(position).namaTanaman)
        holder.tanamankuTimestampTV.setText("Ditanam " + allTanaman.get(position).kapanDitanam)
        holder.tanamankuFotoIV.setImageResource(allTanaman.get(position).fotoTanaman)

        holder.itemView.setOnClickListener {
            tanamankuClickInterface.onTanamanItemClick(allTanaman.get(position))
        }
    }

    fun updateList(newList: List<TanamankuItemModels>) {
        allTanaman.clear()
        allTanaman.addAll(newList)
        notifyDataSetChanged()
    }
}

interface TanamankuClickDeleteInterface {
    fun onDeleteIconClick(tanamanku: TanamankuItemModels)
}

interface TanamankuClickInterface {
    fun onTanamanItemClick(tanamanku: TanamankuItemModels)
}