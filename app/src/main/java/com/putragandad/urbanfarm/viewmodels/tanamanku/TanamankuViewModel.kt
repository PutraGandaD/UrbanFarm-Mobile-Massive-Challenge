package com.putragandad.urbanfarm.viewmodels.tanamanku

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.putragandad.urbanfarm.databases.tanamanku.TanamankuDatabase
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels
import com.putragandad.urbanfarm.repositories.tanamanku.TanamankuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TanamankuViewModel(application: Application)
    : AndroidViewModel(application){
        var allTanaman: LiveData<List<TanamankuItemModels>>
        val repository: TanamankuRepository

        init {
            val dao = TanamankuDatabase.getDatabase(application).getTanamankuDao()
            repository = TanamankuRepository(dao)
            allTanaman = repository.allTanaman
        }

        fun deleteTanaman(tanamanku: TanamankuItemModels) = viewModelScope.launch(Dispatchers.IO) {
            repository.delete(tanamanku)
        }

        fun updateTanaman(tanamanku: TanamankuItemModels) = viewModelScope.launch(Dispatchers.IO) {
            repository.update(tanamanku)
        }

        fun addTanaman(tanamanku: TanamankuItemModels) = viewModelScope.launch(Dispatchers.IO) {
            repository.insert(tanamanku)
        }
}