package com.putragandad.urbanfarm.adapters.jualpanen

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.models.api.JualPanenModel

class JualPanenRVAdapter(
    val jualPanen: ArrayList<JualPanenModel.Data>
) : RecyclerView.Adapter<JualPanenRVAdapter.ViewHolder>()
{
    var onItemClick : ((JualPanenModel.Data) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile : ImageView = itemView.findViewById(R.id.iv_profile_picture)
        val tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        val tvKota: TextView = itemView.findViewById(R.id.tv_kota)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvContent: TextView = itemView.findViewById(R.id.tv_content)
        val ivContent: ImageView = itemView.findViewById(R.id.iv_content)
        val btnWhatsApp: Button = itemView.findViewById(R.id.btn_chat_whatsapp)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jualpanen_card_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: JualPanenRVAdapter.ViewHolder, position: Int) {
        val jualPanen = jualPanen[position]

        val ivContentUrl = jualPanen.contentimg_url
        val ivProfileUrl = jualPanen.profileimg_url
        val whatsappNumber = jualPanen.whatsapp_no

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

        holder.btnWhatsApp.setOnClickListener {
            var waUri: Uri = Uri.parse("https://wa.me/62" + whatsappNumber)
            val intent = Intent(Intent.ACTION_VIEW, waUri)
            holder.itemView.context.startActivity(intent)
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