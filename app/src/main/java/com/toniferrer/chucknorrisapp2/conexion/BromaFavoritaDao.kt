package com.toniferrer.chucknorrisapp2.conexion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BromaFavoritaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bromaFavorita: BromaFavorita)

    @Query("SELECT * FROM broma_favorita")
    fun getTodosFavoritos(): Flow<List<BromaFavorita>>

    @Delete
    suspend fun delete(bromaFavorita: BromaFavorita)
}