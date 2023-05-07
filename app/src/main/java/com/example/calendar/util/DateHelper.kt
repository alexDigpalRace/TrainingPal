package com.example.calendar.util

import java.time.LocalDate
import java.util.Calendar

class DateHelper {
    // helper function to get the local date from the currently set date
    fun getLocalDate(calendar: Calendar): LocalDate {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val date: LocalDate = LocalDate.of(
            year,
            month + 1,
            dayOfMonth,
        )

        return date
    }

    // helper function to set calendar from a local date
    fun setCalendarToLocalDate (calendar: Calendar, localDate: LocalDate) {
        val year = localDate.year
        val month = localDate.monthValue - 1
        val dayOfMonth = localDate.dayOfMonth

        calendar.set(year, month, dayOfMonth)
    }
}