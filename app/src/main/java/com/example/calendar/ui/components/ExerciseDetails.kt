package com.example.calendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
fun ExerciseDetails(modifier: Modifier, exercise: MutableState<Exercise>, isDisplay: Boolean) {
    LazyColumn(
        modifier = modifier
            .background(color = Color.Magenta)
            .height(300.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ExerciseRow(
                modifier = modifier,
                exercisePropertyLabel = "Name: ",
                value = exercise.value.name,
                onValueChange = {/*TODO*/ })
            SetsByRepsRow(
                modifier = modifier,
                label = "Sets x Reps: ",
                sets = exercise.value.sets.toString(),
                onSetsChange = {/*TODO*/ },
                reps = exercise.value.reps.toString(),
                onRepsChange = {/*TODO*/ }
            )
            ExerciseRow(
                modifier = modifier,
                exercisePropertyLabel = "Weight: ",
                value = exercise.value.weight,
                onValueChange = {/*TODO*/ }
            )
            ExerciseRow(
                modifier = modifier,
                exercisePropertyLabel = "Extra details: ",
                value = exercise.value.details,
                onValueChange = {/*TODO*/ })
            ExerciseRow(
                modifier = modifier,
                exercisePropertyLabel = "Comments: ",
                value = exercise.value.comments,
                onValueChange = {/*TODO*/ })
        }
    }
}

@Composable
fun ExerciseRow(
    modifier: Modifier,
    exercisePropertyLabel: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Row(modifier.fillMaxWidth()) {
        Text(text = exercisePropertyLabel, fontSize = 16.sp)
        TextField(value = value, onValueChange = onValueChange)
    }
}

@Composable
fun SetsByRepsRow(
    modifier: Modifier,
    label: String,
    sets: String,
    onSetsChange: (String) -> Unit,
    reps: String,
    onRepsChange: (String) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 16.sp)
        TextField(value = sets, onValueChange = onSetsChange)
        Text(text = " X ")
        TextField(value = reps, onValueChange = onRepsChange)
    }
}