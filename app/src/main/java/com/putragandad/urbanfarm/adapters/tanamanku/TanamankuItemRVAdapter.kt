package com.putragandad.urbanfarm.adapters.tanamanku

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels

class TanamankuItemRVAdapter(val context: Context, val tanamankuClickDeleteInterface: TanamankuClickDeleteInterface) :
    RecyclerView.Adapter<TanamankuItemRVAdapter.ViewHolder>()
{
    var onItemClick : ((TanamankuItemModels, String) -> Unit)? = null
    private val AllTanaman = ArrayList<TanamankuItemModels>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tanamankuTitleTV = itemView.findViewById<TextView>(R.id.tv_title_card_tanamanku_rv)
        val tanamankuTimestampTV = itemView.findViewById<TextView>(R.id.tv_timestamp_card_tanamanku_rv)
        val tanamankuFotoIV = itemView.findViewById<ImageView>(R.id.iv_card_tanamanku_rv)
        val tanamankuDeleteBtn = itemView.findViewById<ImageView>(R.id.iv_delete_tanaman_tanamanku_page)
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
        val itemCount = AllTanaman.size
        saveItemCount(itemCount)
        return itemCount
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tanaman = AllTanaman[position]
        holder.tanamankuTitleTV.text = tanaman.namaTanaman
        holder.tanamankuTimestampTV.text = "Ditanam " + tanaman.kapanDitanam
        holder.tanamankuFotoIV.setImageResource(tanaman.fotoTanaman)
        holder.tanamankuDeleteBtn.setOnClickListener {
            tanamankuClickDeleteInterface.onDeleteIconClick(tanaman)
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(tanaman, tanaman.metodeTanam)
        }
    }

    fun updateList(newList: List<TanamankuItemModels>) {
        AllTanaman.clear()
        AllTanaman.addAll(newList)
        notifyDataSetChanged()
    }

    private fun saveItemCount(itemCount: Int) {
        val sharedPreferences = context.getSharedPreferences("TanamankuCount", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("itemCount", itemCount)
        editor.apply()
    }
}

interface TanamankuClickDeleteInterface {
    fun onDeleteIconClick(tanamanku: TanamankuItemModels)
}
