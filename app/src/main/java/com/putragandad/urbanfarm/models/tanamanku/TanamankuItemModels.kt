package com.putragandad.urbanfarm.models.tanamanku

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tanamanku_list")
data class TanamankuItemModels(
    @ColumnInfo(name = "Nama") val namaTanaman: String,
    @ColumnInfo(name = "Jenis") val jenisTanaman: String,
    @ColumnInfo(name = "Metode") val metodeTanam: String,
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}