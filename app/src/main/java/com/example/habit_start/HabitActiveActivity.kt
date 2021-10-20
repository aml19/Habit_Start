package com.example.habit_start

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HabitActiveActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startButton = findViewById<Button>(R.id.fragment_active_habit_startbutton)
        val pauseButton = findViewById<Button>(R.id.fragment_active_habit_pausebutton)
        startButton.setOnClickListener(this)
        pauseButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}