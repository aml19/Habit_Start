package com.example.habit_start

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.habit_start.ui.datatypes.Habit
import com.example.habit_start.ui.datatypes.datesOfWeek
import java.security.SecureRandom

class MainActivity : AppCompatActivity() {

    //***  Private members

    //***  Public Members
    /*
        Global array that holds all habits of a user.
            -Fragments will edit the Habits inside of the array.
                -updating time worked on, status, etc
            -Only size when a new Habit is created in createHabitFragment()
     */
    lateinit var habits : Array<Habit>

    //The actual array sent to HabitFragment's recyclerView. Could be filtered in HabitsFragment etc.
    lateinit var sharedHabits : Array<Habit>

    val defaultHabit = Habit("NULL", -1, arrayListOf())

    /////////////// End members ////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // set up testing
        habits = testHabits()       //inits array[30] with habits to test out
        sharedHabits = habits

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun setHabitArray(inputHabits : Array<Habit>){
        habits = inputHabits
    }

    ///////////////////////////// For Testing //////////////////
    /*
        Returns Array of random habits
            -random sized name, random duration, random datesOfTheWeek
     */
    fun testHabits() : Array<Habit>{
        val numHabits = 30
        var habits : ArrayList<Habit> = arrayListOf()

        //random string of size numHabits
        val random = SecureRandom()
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        //determine string of random length and chars, and put in numbers
        for(i in 1..numHabits){
            //random numbers for the goal times etc
            val randomGoalTime = (0..999).random()
            val randomChars =  (0..25).random()

            val randomString = Array(randomChars) {
                charPool[random.nextInt(charPool.size)]
            }.joinToString("")

            habits.add(Habit(randomString, randomGoalTime, arrayListOf<datesOfWeek>()))
        }

        return habits.toTypedArray<Habit>()
    }
}