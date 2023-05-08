package com.msusman.littlelemoncapstoneapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msusman.littlelemoncapstoneapp.data.DishRepository
import com.msusman.littlelemoncapstoneapp.data.DishEntity
import kotlinx.coroutines.launch

/**
 * Created by Muhammad Usman : msusman97@gmail.com on 5/8/2023.
 */
class MainViewModel : ViewModel() {
    private val repo = DishRepository()
    val dishes: LiveData<List<DishEntity>> = repo.getAllItems()

    init {
        viewModelScope.launch {
            try {
                repo.fetchMenu()
            } catch (e: Exception) {
                Log.d("MainViewModel ,", "fetchMenu error: ${e.message}")
            }
        }
    }

    fun getDisDetail(id: Int): LiveData<DishEntity?> {
        return repo.getDishDetail(id)
    }
}