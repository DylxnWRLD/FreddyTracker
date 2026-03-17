package com.example.freddytracker.interfaz.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.freddytracker.viewModel.TareaViewModel

@Composable
fun PantallaPrincipal(
    navController: NavController,
    viewModel: TareaViewModel
) {

    Column {

        Text(
            text = "Registro de tiempos",
            fontSize = 24.sp
        )

        LazyColumn {

            items(viewModel.tasks) { task ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(task.name)

                        Text(task.status)

                        Button(
                            onClick = {
                                viewModel.deleteTask(task)
                            }
                        ) {
                            Text("Eliminar")
                        }

                        Button(
                            onClick = {
                                navController.navigate("editTask/${task.id}")
                            }
                        ) {
                            Text("Editar")
                        }

                    }

                }

            }

        }

        Button(
            onClick = {
                navController.navigate("addTask")
            }
        ) {
            Text("Agregar tarea")
        }

    }

}