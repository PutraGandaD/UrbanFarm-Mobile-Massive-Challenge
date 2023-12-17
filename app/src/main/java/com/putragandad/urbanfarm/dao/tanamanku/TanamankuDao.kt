package com.putragandad.urbanfarm.dao.tanamanku

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels

@Dao
interface TanamankuDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tanamanku: TanamankuItemModels)

    @Delete
    suspend fun delete(tanamanku: TanamankuItemModels)

    @Query("Select * from tanamanku_list order by id ASC")
    fun getAllTanaman(): LiveData<List<TanamankuItemModels>>

    @Update
    suspend fun update(tanamanku: TanamankuItemModels)

    @Query("SELECT * FROM tanamanku_list WHERE id = :id")
    suspend fun getTanamankuItemById(id: Int): TanamankuItemModels?
}