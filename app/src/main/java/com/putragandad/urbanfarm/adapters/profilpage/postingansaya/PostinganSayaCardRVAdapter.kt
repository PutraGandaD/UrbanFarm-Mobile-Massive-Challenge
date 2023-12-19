package com.putragandad.urbanfarm.adapters.profilpage.postingansaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.models.api.JualPanenModel

class PostinganSayaCardRVAdapter(
    val jualPanen: ArrayList<JualPanenModel.Data>
) : RecyclerView.Adapter<PostinganSayaCardRVAdapter.ViewHolder>()
{
    var onItemClick : ((JualPanenModel.Data) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile : ImageView = itemView.findViewById(R.id.iv_profile_picture)
        val tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        val tvKota: TextView = itemView.findViewById(R.id.tv_kota)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvContent: TextView = itemView.findViewById(R.id.tv_content)
        val ivContent: ImageView = itemView.findViewById(R.id.iv_content)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_postingansaya_card_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostinganSayaCardRVAdapter.ViewHolder, position: Int) {
        val jualPanen = jualPanen[position]

        val ivContentUrl = jualPanen.contentimg_url
        val ivProfileUrl = jualPanen.profileimg_url

        Glide.with(holder.itemView.context)
            .load(ivProfileUrl)
            .into(holder.ivProfile)

        Glide.with(holder.itemView.context)
            .load(ivContentUrl)
            .into(holder.ivContent)

        holder.tvUsername.text = jualPanen.username
        holder.tvKota.text = jualPanen.kota
        holder.tvTitle.text = jualPanen.title
        holder.tvContent.text = jualPanen.content

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(jualPanen)
        }
    }

    override fun getItemCount(): Int {
        return jualPanen.size
    }

    public fun setData(data: List<JualPanenModel.Data>) {
        jualPanen.clear()
        jualPanen.addAll(data)
        notifyDataSetChanged()
    }

}