package com.putragandad.urbanfarm.models.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JualPanenModel(
    @SerializedName("data")
    @Expose
    val data: List<Data>
) {
    data class Data(
        @SerializedName("id")
        @Expose
        val id: Int?,

        @SerializedName("id_user")
        @Expose
        val id_user: String?,

        @SerializedName("profileimg_url")
        @Expose
        val profileimg_url: String?,

        @SerializedName("username")
        @Expose
        val username: String?,

        @SerializedName("whatsapp_no")
        @Expose
        val whatsapp_no: String?,

        @SerializedName("title")
        @Expose
        val title: String?,

        @SerializedName("content")
        @Expose
        val content: String?,

        @SerializedName("contentimg_url")
        @Expose
        val contentimg_url: String?,

        @SerializedName("kota")
        @Expose
        val kota: String?,

        @SerializedName("jenisTanaman")
        @Expose
        val jenisTanaman: String?,

        @SerializedName("metodeTanam")
        @Expose
        val metodeTanam: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun describeContents(): Int {
            TODO("Not yet implemented")
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id ?: 0)
            parcel.writeString(id_user)
            parcel.writeString(profileimg_url)
            parcel.writeString(username)
            parcel.writeString(whatsapp_no)
            parcel.writeString(title)
            parcel.writeString(content)
            parcel.writeString(contentimg_url)
            parcel.writeString(kota)
            parcel.writeString(jenisTanaman)
            parcel.writeString(metodeTanam)
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }

    }
}
