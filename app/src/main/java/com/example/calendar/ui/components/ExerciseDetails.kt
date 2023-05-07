package com.example.calendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.model.Exercise

// todo: big text boxes for details and comments, check box for isTime,
@Composable
fun ExerciseDetails(modifier: Modifier, exercise: MutableState<Exercise>) {
    LazyColumn(
        modifier = modifier
            .background(color = Color.Magenta)
            .height(300.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Name: ${exercise.value.name}", fontSize = 16.sp)
            Text(
                text = "SxR: ${exercise.value.sets.toString()} x ${exercise.value.reps.toString()}",
                fontSize = 16.sp
            )
            Text(text = "Weight: ${exercise.value.weight}", fontSize = 16.sp)
            Text(text = "Extra details: ", fontSize = 16.sp)
            Text(text = exercise.value.details, fontSize = 16.sp)
            Text(text = "Comments: ", fontSize = 16.sp)
            Text(text = exercise.value.comments, fontSize = 16.sp)
        }
    }
}