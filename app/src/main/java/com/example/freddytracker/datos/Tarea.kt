package com.example.freddytracker.datos

data class Tarea(
    val id: Int,
    var name: String,
    var startTime: String,
    var endTime: String?,
    var status: String
)