package com.putragandad.urbanfarm.databases.tanamanku

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.putragandad.urbanfarm.dao.tanamanku.TanamankuDao
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels

@Database(entities = arrayOf(TanamankuItemModels::class), version = 1, exportSchema = false)
abstract class TanamankuDatabase: RoomDatabase() {
    abstract fun getTanamankuDao(): TanamankuDao

    companion object {
        @Volatile
        private var INSTANCE: TanamankuDatabase? = null

        fun getDatabase(context: Context): TanamankuDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TanamankuDatabase::class.java,
                    "tanamanku_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}