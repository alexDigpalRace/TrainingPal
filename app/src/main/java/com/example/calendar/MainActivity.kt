package com.example.calendar

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.calendar.data.Datasource
import com.example.calendar.model.Day
import com.example.calendar.ui.CalendarView
import com.example.calendar.ui.NewWorkoutView
import com.example.calendar.util.DateHelper
import java.util.*

class MainActivity {
    @Composable
    fun CalendarApp() {
        val context = LocalContext
//        CalendarsTheme {
//        }
    }

    @Preview
    @Composable
    fun NewWorkoutViewPreview() {
        val calendar = Calendar.getInstance()
        val helperLib = DateHelper()
        val day = Day(helperLib.getLocalDate(calendar), "Day", null)
        NewWorkoutView(day = day, modifier = Modifier)
    }

    @Preview
    @Composable
    fun CalendarViewPreview() {
        val mockDatasource = Datasource()
        val calendar = Calendar.getInstance()
//        val currentWeek = mockDatasource.getCurrentWeek(calendar)
        val currentWeek = mockDatasource.mockWeek()

        CalendarView(week = currentWeek, calendar = calendar)
    }

}