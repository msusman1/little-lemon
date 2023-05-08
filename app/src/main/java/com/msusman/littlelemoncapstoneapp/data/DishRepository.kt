package com.msusman.littlelemoncapstoneapp.data

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.msusman.littlelemoncapstoneapp.App
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class DishRepository {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }


    private val database by lazy {
        Room.databaseBuilder(App.context, AppDatabase::class.java, "database").build()
    }

      fun getDishDetail(dishId: Int): LiveData<DishEntity?> {
        return database.menuItemDao().getDish(dishId)
    }

    fun getAllItems(): LiveData<List<DishEntity>> {
        return database.menuItemDao().getAll()
    }

      suspend fun fetchMenu() {
        val url =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val dishesResponse: DishesResponse = httpClient.get(url).body()
        saveMenuToDatabase(dishesResponse.menu)
    }

    private suspend fun saveMenuToDatabase(menuItemsNetwork: List<DishApi>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}