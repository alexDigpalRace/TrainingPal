package com.example.calendar.data

import com.example.calendar.util.DateHelper
import com.example.calendar.model.Day
import com.example.calendar.model.Exercise
import com.example.calendar.model.Week
import com.example.calendar.model.Workout
import java.util.*
import kotlin.collections.ArrayList


// load a year
// split it into weeks
// create lists of those weeks
// track current week
// minus week goes to preview year
class Datasource {

    fun mockWeek(): Week {
        val day1Exercises = arrayListOf<Exercise>()
        day1Exercises.add(Exercise("Mini-run", 1, 15, true, "BW", "warmup for training", ""))
        day1Exercises.add(Exercise("Kb swings", 4, 20, false, "20kg", "1h alternating", ""))
        day1Exercises.add(Exercise("Pull-ups", 4, 5, false, "Bw", "pronated", "changed grip distance per set"))
        day1Exercises.add(Exercise("Vertical pull-ups", 4, 10, false, "bw", "", "went to failure"))
        day1Exercises.add(Exercise("Kb rows", 4, 10, false, "16kg", "details", "comments"))

        val day2Exercises = arrayListOf<Exercise>()
        day2Exercises.add(Exercise("Mini-run", 1, 15, true, "BW", "warmup for training", ""))
        day1Exercises.add(Exercise("Split squats", 4, 8, false, "2x16kg", "reps done on both sides", ""))
        day2Exercises.add(Exercise("Front squats", 5, 10, false, "2x16kg", "brace", ""))
        day2Exercises.add(Exercise("Tib and calf raises", 3, 10, false, "2x16kg", "", "need to change exercise class so it can handle super sets, multiple weights"))

        val day3Exercises = arrayListOf<Exercise>()
        day3Exercises.add(Exercise("Mini-run", 1, 15, true, "BW", "warmup for training", ""))
        day3Exercises.add(Exercise("Kb clean & press", 4, 8, false, "16kg", "do both sides per set", ""))
        day3Exercises.add(Exercise("Dips", 4, 12, false, "BW", "", ""))
        day3Exercises.add(Exercise("Push-ups", 4, 10, false, "BW", "", ""))
        day3Exercises.add(Exercise("Y-raises", 4, 8, false, "16kg", "standing, lean so near parallel with floor, slow and controlled", ""))
        day3Exercises.add(Exercise("Leg raises", 4, 8, false, "BW", "", ""))

        val day4Exercises = arrayListOf<Exercise>()
        day4Exercises.add(Exercise("Mini-run", 1, 15, true, "BW", "warmup for training", ""))
        day4Exercises.add(Exercise("Straight leg deadlift", 4, 10, false, "60kg", "slow and controlled, warmup for deadlifts", ""))
        day4Exercises.add(Exercise("Deadlift", 5, 8, false, "90kg", "brace, break bar", ""))
        day4Exercises.add(Exercise("Back extension", 4, 8, false, "BW", "slow and controlled, 1L", ""))
        day4Exercises.add(Exercise("Tib and calf raises", 3, 10, false, "60kg", "", "need to change exercise class so it can handle super sets, multiple weights"))

        val calendar = Calendar.getInstance()

        var week = getCurrentWeek(calendar)
        week.days.get(1).workout = Workout("","", day1Exercises)
        week.days.get(2).workout = Workout("","", day2Exercises)
        week.days.get(3).workout = Workout("","", day3Exercises)
        week.days.get(4).workout = Workout("","", day4Exercises)
        return week
    }

    fun mockWorkout(): Workout {
        val exercises: MutableList<Exercise> = mutableListOf()

        exercises.add(Exercise("Kb swings", 4, 20, false, "20kg", "1h alternating", ""))
        exercises.add(Exercise("Pull-ups", 4, 5, false, "Bw", "pronated", "changed grip distance per set"))
        exercises.add(Exercise("Vertical pull-ups", 4, 10, false, "bw", "", "went to failure"))
        exercises.add(Exercise("Kb rows", 4, 10, false, "16kg", "details", "comments"))
        val workout = Workout("09:00", "10:30", ArrayList(exercises))

        return workout
    }

    fun getCurrentWeek(calendar: Calendar): Week {
        val days: MutableList<Day> = mutableListOf()

        val firstDayOfWeekIndex = calendar.firstDayOfWeek

        val currentDayOfWeekIndex = calendar.get(Calendar.DAY_OF_WEEK)
        val daysFromFirst = currentDayOfWeekIndex - firstDayOfWeekIndex
        // first day is SUNDAY, not ideal
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_YEAR) - daysFromFirst
        // start at the first of the week
        calendar.set(Calendar.DAY_OF_YEAR, firstDayOfWeek)

        val helperLib = DateHelper()
        for (i in 0..6) {
            val date = helperLib.getLocalDate(calendar)

            val dayOfWeekInitial = calendar.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.LONG_FORMAT,
                Locale.getDefault()
            )


            days.add(Day(date, dayOfWeekInitial, null))
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1)
        }

        val weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR)

        return Week(weekOfYear, days)
    }
}