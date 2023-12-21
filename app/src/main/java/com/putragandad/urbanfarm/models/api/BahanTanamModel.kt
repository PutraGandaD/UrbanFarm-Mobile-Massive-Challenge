package com.putragandad.urbanfarm.models.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BahanTanamModel(
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

        @SerializedName("url_beli")
        @Expose
        val url_beli: String?,

        @SerializedName("url_gambar")
        @Expose
        val url_gambar: String?,

        @SerializedName("jenisTanaman")
        @Expose
        val jenisTanaman: String?,

        @SerializedName("metodeTanam")
        @Expose
        val metodeTanam: String?,
    )
}
