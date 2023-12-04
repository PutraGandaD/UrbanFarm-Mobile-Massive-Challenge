package com.putragandad.urbanfarm.models.beranda

import android.os.Parcel
import android.os.Parcelable

data class ItemTanamanModels(
    val iconTanaman: Int,
    val namaTanaman: String,
    val jenisTanaman: String,
    val teknikMenanam: String,
    val tingkatKesulitan: String,
    val deskripsiTanaman: String,
    val fotoTanaman: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(iconTanaman)
        parcel.writeString(namaTanaman)
        parcel.writeString(jenisTanaman)
        parcel.writeString(teknikMenanam)
        parcel.writeString(tingkatKesulitan)
        parcel.writeString(deskripsiTanaman)
        parcel.writeInt(fotoTanaman)
    }

    companion object CREATOR : Parcelable.Creator<ItemTanamanModels> {
        override fun createFromParcel(parcel: Parcel): ItemTanamanModels {
            return ItemTanamanModels(parcel)
        }

        override fun newArray(size: Int): Array<ItemTanamanModels?> {
            return arrayOfNulls(size)
        }
    }

}
