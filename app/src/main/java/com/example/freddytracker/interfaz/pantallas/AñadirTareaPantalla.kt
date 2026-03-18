package com.example.freddytracker.interfaz.pantallas

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
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Agregar Tarea",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre Tarea") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                val formattedTime = sdf.format(Date())
                val task = Tarea(
                    id = viewModel.tasks.size + 1,
                    name = name,
                    startTime = formattedTime,
                    endTime = null,
                    tiempoAcumulado = 0L,
                    ultimoInicio = 0L,
                    estado = EstadoTarea.PENDIENTE
                )
                viewModel.addTask(task)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFffcf4d),
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Agregar Tarea",
                fontSize = 19.sp
            )
        }
    }
}