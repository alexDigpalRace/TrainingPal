package com.example.calendar.model

import java.time.LocalDate

data class Day(
    val date: LocalDate,
    val dayName: String,
    var workout: Workout?,
)
