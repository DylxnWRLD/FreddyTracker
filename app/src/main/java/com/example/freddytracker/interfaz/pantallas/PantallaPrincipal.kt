package com.example.freddytracker.interfaz.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.freddytracker.datos.EstadoTarea
import com.example.freddytracker.datos.Tarea
import com.example.freddytracker.viewModel.TareaViewModel
import kotlinx.coroutines.delay

@Composable
fun PantallaPrincipal(
    navController: NavController,
    viewModel: TareaViewModel
) {

    var tareaAEliminar by remember { mutableStateOf<Tarea?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Registro de \ntiempos",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(viewModel.tasks) { task ->

                var tiempoActual by remember {
                    mutableStateOf(viewModel.obtenerTiempoActual(task))
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        // Actualiza el tiempo cada segundo
                        LaunchedEffect(task.estado) {
                            while (true) {
                                if (task.estado == EstadoTarea.EN_PROGRESO) {
                                    tiempoActual = viewModel.obtenerTiempoActual(task)
                                }
                                delay(1000)
                            }
                        }

                        Text(task.name)
                        Text(task.estado.name)
                        Text(text = formatearTiempo(tiempoActual))

                        Spacer(modifier = Modifier.height(8.dp))

                        when (task.estado) {

                            EstadoTarea.PENDIENTE -> {
                                Button(onClick = {
                                    viewModel.iniciarTarea(task)
                                }) {
                                    Text("Iniciar")
                                }
                            }

                            EstadoTarea.EN_PROGRESO -> {
                                Button(onClick = {
                                    viewModel.pausarTarea(task)
                                }) {
                                    Text("Pausar")
                                }
                            }

                            EstadoTarea.PAUSADO -> {
                                Button(onClick = {
                                    viewModel.reanudarTarea(task)
                                }) {
                                    Text("Reanudar")
                                }
                            }

                            else -> {}
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            tareaAEliminar = task
                        }) {
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

        if (tareaAEliminar != null) {
            AlertDialog(
                onDismissRequest = { tareaAEliminar = null },
                title = { Text("Confirmar eliminación") },
                text = {
                    Text("¿Seguro que quieres eliminar ${tareaAEliminar!!.name}?")
                },
                dismissButton = {
                    Button(onClick = {
                        tareaAEliminar = null
                    }) {
                        Text("Cancelar")
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.deleteTask(tareaAEliminar!!)
                        tareaAEliminar = null
                    }) {
                        Text("Sí, eliminar")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("addTask")
            }
        ) {
            Text("Agregar tarea")
        }
    }
}

fun formatearTiempo(millis: Long): String {
    val segundos = (millis / 1000) % 60
    val minutos = (millis / (1000 * 60)) % 60
    val horas = (millis / (1000 * 60 * 60))

    return String.format("%02d:%02d:%02d", horas, minutos, segundos)
}