package com.example.calendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.calendar.model.Exercise
import com.example.calendar.model.Workout
import com.example.workoutdigpal.R

@Composable
fun WorkoutDetails(
    modifier: Modifier, workout: Workout?, onExerciseChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.Green)
            .height(350.dp)
            .fillMaxSize()
    ) {
        WorkoutTimeInput(
            label = stringResource(R.string.startTimeLabel), workout?.startTime ?: "No workout"
        )
        WorkoutTimeInput(
            label = stringResource(R.string.endTimeLabel), workout?.endTime ?: "No workout"
        )
        ExercisesList(
            modifier = modifier, exercises = workout?.exercises ?: ArrayList(
                listOf(
                    Exercise(
                        "No workout", 0, 0, false, "", "", ""
                    )
                )
            ), onExerciseChange = onExerciseChange
        )
    }
}

@Composable
fun ExercisesList(
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

@Composable
fun WorkoutTimeInput(label: String, value: String) {
    val valueTextField = TextFieldValue(value)
    Row {
        TextField(value = valueTextField, onValueChange = {/*todo*/ }, label = { Text(label) })
        Button(onClick = { /*TODO*/ }) {
            Text(text = if ("start" in label.lowercase()) "Start now" else "End now")
        }
    }
}