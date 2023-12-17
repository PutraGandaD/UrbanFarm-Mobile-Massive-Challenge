package com.putragandad.urbanfarm.models.tanamanku

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tanamanku_list")
data class TanamankuItemModels(
    @ColumnInfo(name = "Nama") val namaTanaman: String,
    @ColumnInfo(name = "Jenis") val jenisTanaman: String,
    @ColumnInfo(name = "Metode") val metodeTanam: String,
    @ColumnInfo(name = "Waktu") val kapanDitanam: String,
    @ColumnInfo(name = "Foto") val fotoTanaman: Int,
    @ColumnInfo(name = "idTanaman") val idTanaman: String,
    @ColumnInfo(name = "switchSiramTanamanState") val switchSiramTanaman: Boolean = false,
    @ColumnInfo(name = "switchCekTanamanState") val switchCekTanaman: Boolean = false
) : Parcelable {
    @PrimaryKey(autoGenerate = true) var id = 0

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt() == 1,
        parcel.readInt() == 1
    ) {
        id = parcel.readInt()
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namaTanaman)
        parcel.writeString(jenisTanaman)
        parcel.writeString(metodeTanam)
        parcel.writeString(kapanDitanam)
        parcel.writeInt(fotoTanaman)
        parcel.writeString(idTanaman)
        parcel.writeInt(if(switchSiramTanaman) 1 else 0)
        parcel.writeInt(if(switchCekTanaman) 1 else 0)
        parcel.writeInt(id)
    }

    companion object CREATOR : Parcelable.Creator<TanamankuItemModels> {
        override fun createFromParcel(parcel: Parcel): TanamankuItemModels {
            return TanamankuItemModels(parcel)
        }

        override fun newArray(size: Int): Array<TanamankuItemModels?> {
            return arrayOfNulls(size)
        }
    }
}