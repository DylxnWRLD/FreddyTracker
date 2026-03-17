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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freddytracker.datos.Tarea
import com.example.freddytracker.viewModel.TareaViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun AñadirTareaPantalla(
    navController: NavController,
    viewModel: TareaViewModel
) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Agregar tarea")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de tarea") }
        )


        Button(
            onClick = {
                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                val formattedTime = sdf.format(Date())

                val task = Tarea(
                    id = viewModel.tasks.size + 1,
                    name = name,
                    startTime = formattedTime, // Se asigna automáticamente
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