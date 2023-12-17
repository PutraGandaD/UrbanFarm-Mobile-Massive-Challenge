package com.putragandad.urbanfarm.repositories.tanamanku

import androidx.lifecycle.LiveData
import com.putragandad.urbanfarm.dao.tanamanku.TanamankuDao
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels

class TanamankuRepository(private val tanamankuDao: TanamankuDao) {
    val allTanaman: LiveData<List<TanamankuItemModels>> = tanamankuDao.getAllTanaman()

    suspend fun insert(tanamanku: TanamankuItemModels) {
        tanamankuDao.insert(tanamanku)
    }

    suspend fun delete(tanamanku: TanamankuItemModels) {
        tanamankuDao.delete(tanamanku)
    }

    suspend fun update(tanamanku: TanamankuItemModels) {
        tanamankuDao.update(tanamanku)
    }

    suspend fun getTanamankuItemById(id: Int): TanamankuItemModels? {
        return tanamankuDao.getTanamankuItemById(id)
    }
}