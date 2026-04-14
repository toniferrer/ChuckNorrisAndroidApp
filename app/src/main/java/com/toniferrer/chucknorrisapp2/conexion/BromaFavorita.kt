package com.toniferrer.chucknorrisapp2.conexion

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "broma_favorita")
data class BromaFavorita (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val idBroma: String,
        val textoBroma: String,
        val categoria: String? = null
    )