package com.toniferrer.chucknorrisapp2.paginas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.toniferrer.chucknorrisapp2.conexion.BromaFavorita
import com.toniferrer.chucknorrisapp2.conexion.BromaViewModel
import kotlin.collections.emptyList

@Composable
fun FavoritosScreen (navController: NavController, viewModel: BromaViewModel = viewModel()) {
    val misFavoritos by viewModel.todosFavorita.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tus favoritos",
                style = MaterialTheme.typography.labelLarge,
            )

            Button(
                onClick = { navController.navigate("PrincipalScreen") },
            ) {
                Text("Volver")
            }
        }

        LazyColumn {
            items(misFavoritos ?: emptyList()) { broma ->
                BromaFavorita(
                    broma = broma,
                    onDelete = {
                        viewModel.eliminarFavorito(broma)
                    }
                )
            }
        }
    }
}

@Composable
fun BromaFavorita(broma: BromaFavorita, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = broma.textoBroma,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        broma.categoria?.let { categoria ->
            Text(text = "Categoría: $categoria", style = MaterialTheme.typography.bodyMedium)
            IconButton(
                onClick = onDelete,
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar"
                )
            }
        }
    }
}