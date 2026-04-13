package com.toniferrer.chucknorrisapp2.paginas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.toniferrer.chucknorrisapp2.conexion.BromaViewModel
import java.util.Locale.getDefault

@Composable
fun CategoriaScreen (category: String,navController: NavController, viewModel: BromaViewModel = viewModel()) {
    val categoriBroma by viewModel.categoriaBroma.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchBromaPorCategoria(category)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 66.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Categoría: $category",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = categoriBroma?.value ?: "Cargando...",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Button(
                onClick = {
                    viewModel.fetchBromaPorCategoria(category)
                },
                modifier = Modifier
            ) {
                Text("Nuevo chiste")
            }

            Button(
                onClick = {
                    navController.navigate("PrincipalScreen")
                },
                modifier = Modifier
            ) {
                Text("Volver")
            }
        }
    }
}