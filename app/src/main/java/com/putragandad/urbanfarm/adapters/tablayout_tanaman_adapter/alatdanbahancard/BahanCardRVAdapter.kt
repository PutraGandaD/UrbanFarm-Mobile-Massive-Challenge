package com.putragandad.urbanfarm.adapters.tablayout_tanaman_adapter.alatdanbahancard

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.models.api.AlatTanamModel
import com.putragandad.urbanfarm.models.api.BahanTanamModel

class BahanCardRVAdapter(
    val bahanList: ArrayList<BahanTanamModel.Data>
) : RecyclerView.Adapter<BahanCardRVAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCard: ImageView = itemView.findViewById(R.id.iv_alat_card)
        val tvCard: TextView = itemView.findViewById(R.id.tv_title_alat_card)
        val btnBeli: MaterialButton = itemView.findViewById(R.id.btn_beli_online_alat_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alat_bahan_panduantanam_card_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bahanList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listBahan = bahanList[position]

        val linkBeliUrl = listBahan.url_beli
        val linkGambarUrl = listBahan.url_gambar

        holder.tvCard.text = listBahan.title // set title
        Glide.with(holder.itemView.context)
            .load(linkGambarUrl)
            .centerCrop()
            .error(R.drawable.logo)
            .placeholder(R.drawable.logo)
            .into(holder.ivCard)

        holder.btnBeli.setOnClickListener {
            var beliUri: Uri = Uri.parse(linkBeliUrl)
            val intent = Intent(Intent.ACTION_VIEW, beliUri)
            holder.itemView.context.startActivity(intent)
        }
    }

    public fun setData(data: List<BahanTanamModel.Data>) {
        bahanList.clear()
        bahanList.addAll(data)
        notifyDataSetChanged()
        Log.d("BahanCardRVAdapter", "Data set updated. Item count: ${itemCount}")
    }
}