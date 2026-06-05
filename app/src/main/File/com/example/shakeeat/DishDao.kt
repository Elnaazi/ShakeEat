package com.example.shakeeat

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {
    @Query("SELECT * FROM dishes")
    fun getAllDishes():Flow<List<Dish>>

    @Query("SELECT * FROM dishes WHERE mood = :mood")
    fun getDishesByMood(mood: String): Flow<List<Dish>>

    @Insert
    suspend fun insertAll(dishes: Dish)

    @Delete
    suspend fun delete(dishes: Dish)

    @Update
    suspend fun update(dish: Dish)
}