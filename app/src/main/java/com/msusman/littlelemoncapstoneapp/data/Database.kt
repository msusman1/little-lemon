package com.msusman.littlelemoncapstoneapp.data

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "dish")
data class DishEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM dish")
    fun getAll(): LiveData<List<DishEntity>>

    @Query("SELECT * FROM dish where id=:dishId")
    fun getDish(dishId: Int): LiveData<DishEntity?>

    @Insert
    suspend fun insertAll(vararg menuItems: DishEntity)

    @Query("SELECT (SELECT COUNT(*) FROM dish) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [DishEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}
