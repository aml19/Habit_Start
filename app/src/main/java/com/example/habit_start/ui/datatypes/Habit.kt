package com.example.habit_start.ui.datatypes

import java.time.Duration
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

/**
 * Data class that holds information about each habit the user wants
 * MainActivity holds the global list of these for the User
 * Name is the unique identifier, so Names have to be unique on creation
 */

data class Habit (val name : String, val goalTime : Int, val dates : ArrayList<datesOfWeek>) {

    enum class statusENUM {
        IN_PROGRESS,
        COMPLETED,
        INCOMPLETE          //when day is over and goal time isn't reached
    }




    var status = statusENUM.IN_PROGRESS     //task status, linked to color
    var duration: Int = 0                   //total time worked on this habit
    var activeTime: Int = 0                 //total time habit is running
    var focusedTime : Int = 0               //time habit is running and app is top concentration
}