package com.example.habit_start.ui.datatypes

enum class HabitType {
    TODO {
        val info = "TODO"
    },
    DAILY {
        val info = "habit"
    },
    PROJECT {
        val info = "project"
    }
}

enum class datesOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}