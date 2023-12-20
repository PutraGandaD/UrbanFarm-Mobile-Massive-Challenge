package com.putragandad.urbanfarm.adapters.beranda

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.models.api.JualPanenModel
import com.putragandad.urbanfarm.models.api.VideosDashboardModel


class VideosRVAdapter(
    val videos: ArrayList<VideosDashboardModel.Data>
) : RecyclerView.Adapter<VideosRVAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivVideo: ImageView = itemView.findViewById(R.id.iv_video_dashboard)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_video_title)
        val tvChannel: TextView = itemView.findViewById(R.id.tv_video_channel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_dashboard_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videosList = videos[position]

        val videosID = videosList.videourl
        val imageUrl = videosList.imgurl

        holder.tvTitle.text = videosList.title
        holder.tvChannel.text = videosList.source

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .error(R.drawable.logo)
            .into(holder.ivVideo)

        holder.itemView.setOnClickListener {
            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videosID))
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videosID))
            try {
                holder.itemView.context.startActivity(intentApp)
            } catch (ex: ActivityNotFoundException) {
                holder.itemView.context.startActivity(intentBrowser)
            }
        }
    }

    public fun setData(data: List<VideosDashboardModel.Data>) {
        videos.clear()
        videos.addAll(data)
        notifyDataSetChanged()
    }
}