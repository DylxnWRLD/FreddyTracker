package com.example.freddytracker.interfaz.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.freddytracker.datos.EstadoTarea
import com.example.freddytracker.viewModel.TareaViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("NewApi")
@Composable
fun EditarTarea(
    navController: NavController,
    viewModel: TareaViewModel,
    taskId: Int
) {
    val task = viewModel.tasks.find { it.id == taskId } ?: return

    var name by remember { mutableStateOf(task.name) }
    var startTime by remember { mutableStateOf(task.startTime) }
    var endTime by remember { mutableStateOf(task.endTime ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Modificar Tarea",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre Tarea") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = startTime,
            onValueChange = { startTime = it },
            label = { Text("Hora inicio") },
            modifier = Modifier.fillMaxWidth()
        )

        if (endTime.isNotEmpty()) {
            Text(
                text = "Hora de fin: $endTime",
                modifier = Modifier.padding(vertical = 12.dp),
                fontSize = 16.sp
            )
        }

        Button(
            onClick = {
                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                endTime = sdf.format(Date())
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFffcf4d),
                contentColor = Color.Black
            )
        ) {
            Text("Registrar hora de fin")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                var tiempoFinal = task.tiempoAcumulado
                var estadoFinal = task.estado

                if (task.estado == EstadoTarea.EN_PROGRESO && endTime.isNotEmpty()) {
                    val ahora = System.currentTimeMillis()
                    tiempoFinal += (ahora - task.ultimoInicio)
                    estadoFinal = EstadoTarea.FINALIZADO
                }

                val updatedTask = task.copy(
                    name = name,
                    startTime = startTime,
                    endTime = if (endTime.isEmpty()) null else endTime,
                    estado = estadoFinal,
                    tiempoAcumulado = tiempoFinal
                )
                viewModel.updateTask(updatedTask)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFffcf4d),
                contentColor = Color.Black
            )
        ) {
            Text("Guardar cambios")
        }
    }
}