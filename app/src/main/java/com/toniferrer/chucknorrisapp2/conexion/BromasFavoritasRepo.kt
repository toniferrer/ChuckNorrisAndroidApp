package com.toniferrer.chucknorrisapp2.conexion

import kotlinx.coroutines.flow.Flow

class BromasFavoritasRepo(private val bromaFavoritaDao: BromaFavoritaDao) {
    val todosFavoritos: Flow<List<BromaFavorita>> = bromaFavoritaDao.getTodosFavoritos()

    suspend fun anadir(bromaFavorita: BromaFavorita) {
        bromaFavoritaDao.insert(bromaFavorita)
    }

    suspend fun eliminar(bromaFavorita: BromaFavorita) {
        bromaFavoritaDao.delete(bromaFavorita)
    }
}