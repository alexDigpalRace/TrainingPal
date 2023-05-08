package com.example.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.calendar.model.Day
import com.example.calendar.model.Exercise
import com.example.calendar.ui.components.ExerciseDetails
import com.example.calendar.ui.components.ExerciseList
import com.example.calendar.util.DateHelper
import com.example.workoutdigpal.R
import java.util.*
import kotlin.collections.ArrayList

@Preview
@Composable
fun NewWorkoutViewPreview() {
    val calendar = Calendar.getInstance()
    val helperLib = DateHelper()
    val day = Day(helperLib.getLocalDate(calendar), "Day", null)
    NewWorkoutView(day = day, modifier = Modifier)
}

@Composable
fun NewWorkoutView(day: Day, modifier: Modifier) {
    val (isAdd, setIsAdd) = remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = stringResource(R.string.createWorkout),
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        Box(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.Green)
        ) {
            ExerciseListOrInput(modifier = modifier, isAdd = isAdd)
            Button(onClick = { setIsAdd(true) }, modifier.align(Alignment.BottomCenter)) {
                Text(text = stringResource(R.string.addExercise))
            }
        }
    }
}

@Composable
fun ExerciseListOrInput(modifier: Modifier, isAdd: Boolean) {
    val exercise = remember {
        mutableStateOf(
            Exercise("", 0, 0, false, "", "", "")
        )
    }

    if (isAdd) {
        ExerciseDetails(modifier = modifier, exercise = exercise, isDisplay = false)
    } else {
        ExerciseList(
            modifier = modifier
                .background(Color.Red)
                .fillMaxSize(),
            exercises = ArrayList(),
            onExerciseChange = {})
    }
}