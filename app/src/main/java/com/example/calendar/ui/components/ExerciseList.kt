package com.example.calendar.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calendar.model.Exercise

@Composable
fun ExerciseList(
    modifier: Modifier, exercises: ArrayList<Exercise>, onExerciseChange: (String) -> Unit
) {
    LazyColumn() {
        items(exercises) { exercise ->
            Button(onClick = { onExerciseChange(exercise.name) }) {
                Text(text = exercise.name)
            }
        }
    }
}
