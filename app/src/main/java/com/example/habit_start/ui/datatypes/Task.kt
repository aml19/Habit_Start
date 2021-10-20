package com.example.habit_start.ui.datatypes

import java.util.*

data class Task(val name : String) {

    val date_created : Calendar = Calendar.getInstance()        //creation date
    var completed : Boolean = false

    /////Possible extra things that I might not add because tasks should be simple
    var time_worked  = System.currentTimeMillis()               //time worked in focus on the task

}