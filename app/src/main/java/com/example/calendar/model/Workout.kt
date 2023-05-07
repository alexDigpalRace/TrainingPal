package com.example.calendar.model

data class Workout(
    val startTime: String,
    val endTime: String,
    val exercises: ArrayList<Exercise>,
)
