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
import com.example.freddytracker.viewModel.TareaViewModel

@Composable
fun EditarTarea(
    navController: NavController,
    viewModel: TareaViewModel,
    taskId: Int
) {

    val task = viewModel.tasks.find { it.id == taskId }

    // Si no existe la tarea
    if (task == null) {
        Text("Tarea no encontrada")
        return
    }

    var name by remember { mutableStateOf(task.name) }
    var startTime by remember { mutableStateOf(task.startTime) }
    var endTime by remember { mutableStateOf(task.endTime ?: "") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text("Editar tarea")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") }
        )

        OutlinedTextField(
            value = startTime,
            onValueChange = { startTime = it },
            label = { Text("Hora inicio") }
        )

        OutlinedTextField(
            value = endTime,
            onValueChange = { endTime = it },
            label = { Text("Hora fin") }
        )

        Button(
            onClick = {

                val updatedTask = task.copy(
                    name = name,
                    startTime = startTime,
                    endTime = if (endTime.isEmpty()) null else endTime,
                    status = if (endTime.isEmpty()) "En progreso" else "Finalizada"
                )

                viewModel.updateTask(updatedTask)

                navController.popBackStack()

            }
        ) {
            Text("Guardar cambios")
        }

    }

}