package com.example.workoutdigpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutdigpal.ui.theme.WorkoutDigpalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutDigpalTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
                WorkoutDigpalApp()
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.cartoon_workout_background),
            contentDescription = "cartoon workout background",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )
//        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome Emperor Alex",
            fontSize = 36.sp,
            modifier = Modifier
                .wrapContentWidth(Alignment.Start)
                .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
                .background(color = Color.White)
        )
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Calendar")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Timer")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutDigpalApp() {
    WorkoutDigpalTheme {
        HomeScreen()
    }
}