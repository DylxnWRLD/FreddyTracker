package com.example.freddytracker.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.freddytracker.datos.Tarea

class TareaViewModel : ViewModel() {

    var tasks = mutableStateListOf<Tarea>()
        private set

    fun addTask(task: Tarea) {
        tasks.add(task)
    }

    fun deleteTask(task: Tarea) {
        tasks.remove(task)
    }

    fun updateTask(updatedTask: Tarea) {
        val index = tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            tasks[index] = updatedTask
        }
    }

}