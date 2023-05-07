package com.example.calendar.model

data class Exercise(
    val name: String,
    val sets: Int,
    val reps: Int,
    val isTime: Boolean,
    val weight: String,
    val details: String,
    val comments: String,
)
