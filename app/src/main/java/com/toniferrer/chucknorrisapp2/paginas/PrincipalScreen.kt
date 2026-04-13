package com.toniferrer.chucknorrisapp2.paginas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun PrincipalScreen (navController: NavController, viewModel: BromaViewModel = viewModel()) {
    val bromaRandom by viewModel.bromaRandom.collectAsState()
    val categorias by viewModel.categorias.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchBromaRandom()
        viewModel.fetchCategorias()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 66.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = bromaRandom?.value ?: "Cargando...",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.fetchBromaRandom()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Nuevo chiste")
        }

        Text(
            text = "Descubre bromas por categorias:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn {
            items(categorias ?: emptyList()) { category ->
                Categoria(
                    category = category.uppercase(getDefault()),
                    onClick = {
                        navController.navigate("categoriaScreen/$category")
                    }
                )
            }
        }
    }
}

@Composable
fun Categoria(category: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}