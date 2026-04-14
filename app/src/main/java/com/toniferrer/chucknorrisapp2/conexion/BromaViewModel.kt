package com.toniferrer.chucknorrisapp2.conexion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.toniferrer.chucknorrisapp2.ChuckNorrisBroma
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class BromaViewModel (application: Application) : AndroidViewModel(application) {
    private val repo : BromasFavoritasRepo
    val todosFavorita: Flow<List<BromaFavorita>>
    private val api = ChuckApi.service

    private val _bromaRandom = MutableStateFlow<ChuckNorrisBroma?>(null)
    val bromaRandom: StateFlow<ChuckNorrisBroma?> = _bromaRandom
    private val _categorias = MutableStateFlow<List<String>?>(null)
    val categorias: StateFlow<List<String>?> = _categorias
    private val _categoriaBroma = MutableStateFlow<ChuckNorrisBroma?>(null)
    val categoriaBroma: StateFlow<ChuckNorrisBroma?> = _categoriaBroma

    init {
        val bromaFavoritaDao = AppDatabase.getDatabase(application).bromaFavoritaDao()
        repo = BromasFavoritasRepo(bromaFavoritaDao)
        todosFavorita = repo.todosFavoritos
    }

    fun anadirFavorito(broma: ChuckNorrisBroma) {
        viewModelScope.launch {
            repo.anadir(
                BromaFavorita(
                    idBroma = broma.id,
                    textoBroma = broma.value,
                    categoria = broma.categories.firstOrNull()
                )
            )
        }
    }

    fun eliminarFavorito(bromaFavorita: BromaFavorita) {
        viewModelScope.launch {
            repo.eliminar(bromaFavorita)
        }
    }

    fun fetchBromaRandom() {
        viewModelScope.launch {
            try {
                _bromaRandom.value = api.getBroma()
            } catch (e: Exception) {

            }
        }
    }

    fun fetchCategorias() {
        viewModelScope.launch {
            try {
                _categorias.value = api.getCategorias()
            } catch (e: Exception) {

            }
        }
    }

    fun fetchBromaPorCategoria(categoria: String) {
        viewModelScope.launch {
            try {
                _categoriaBroma.value = api.getBromaPorCategoria(categoria)
            } catch (e: Exception) {

            }
        }
    }
}