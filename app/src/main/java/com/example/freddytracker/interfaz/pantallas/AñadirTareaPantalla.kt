package com.example.freddytracker.interfaz.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freddytracker.datos.Tarea
import com.example.freddytracker.viewModel.TareaViewModel

@Composable
fun AñadirTareaPantalla(
    navController: NavController,
    viewModel: TareaViewModel
) {

    var name by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text("Agregar tarea")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de tarea") }
        )

        OutlinedTextField(
            value = startTime,
            onValueChange = { startTime = it },
            label = { Text("Hora inicio") }
        )

        Button(
            onClick = {

                val task = Tarea(
                    id = viewModel.tasks.size + 1,
                    name = name,
                    startTime = startTime,
                    endTime = null,
                    status = "En progreso"
                )

                viewModel.addTask(task)

                navController.popBackStack()

            }
        ) {
            Text("Guardar")
        }

    }

}