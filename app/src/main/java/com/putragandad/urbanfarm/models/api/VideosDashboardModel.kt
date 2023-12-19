package com.putragandad.urbanfarm.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideosDashboardModel(
    @SerializedName("data")
    @Expose
    val data: List<Data>
) {
    data class Data(
        @SerializedName("id")
        @Expose
        val id: Int?,

        @SerializedName("title")
        @Expose
        val title: String?,

        @SerializedName("imgurl")
        @Expose
        val imgurl: String?,

        @SerializedName("videourl")
        @Expose
        val videourl: String?,

        @SerializedName("source")
        @Expose
        val source: String?
    )
}
