package com.example.habit_start.ui.habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.habit_start.MainActivity
import com.example.habit_start.R
import com.example.habit_start.ui.datatypes.Habit
import org.w3c.dom.Text
import java.text.FieldPosition

/*
    This fragment displays a stopwatch that counts how long th
    user is working on this habit
 */

class HabitActiveFragment : Fragment(), View.OnClickListener{
    private var pos : Int = -1               //should be unsigned, but can't pass through fragment.bundle...
    private var habit : Habit = (activity as MainActivity).defaultHabit
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get information from HabitFragment
        val bundle = this.arguments
        if (bundle != null) {
            pos = bundle.getInt("position", -1)
            if(pos >= 0)
                habit = (activity as MainActivity).sharedHabits[pos]
        }


        val root = inflater.inflate(R.layout.fragment_active_habit, container, false)
        val startButton = root.findViewById<Button>(R.id.fragment_active_habit_startbutton)
        val pauseButton = root.findViewById<Button>(R.id.fragment_active_habit_pausebutton)
        val nameText = root.findViewById<TextView>(R.id.fragment_active_habit_name)
        nameText.text = habit.name
        startButton.setOnClickListener(this)
        pauseButton.setOnClickListener(this)
        return root
    }
    override fun onClick(v: View?) {

        when(v?.id){
            R.id.fragment_active_habit_pausebutton -> {
                v.findViewById<Chronometer>(R.id.fragment_active_habit_chronometer).stop()
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()}
            R.id.fragment_active_habit_startbutton -> { v.findViewById<Chronometer>(R.id.fragment_active_habit_startbutton).start() }

        }
    }

}
////////////////////////// Stopwatch code taken from https://akjaw.com/kotlin-coroutine-flow-stopwatch-part1/
/*
sealed class StopwatchState {

    data class Paused(
        val elapsedTime: Long
    ) : StopwatchState()

    data class Running(
        val startTime: Long,
        val elapsedTime: Long
    ) : StopwatchState()
}

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator
) {

    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running =
        when (oldState) {
            is StopwatchState.Running -> oldState
            is StopwatchState.Paused -> {
                StopwatchState.Running(
                    startTime = timestampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: StopwatchState): StopwatchState.Paused =
        when (oldState) {
            is StopwatchState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                StopwatchState.Paused(elapsedTime = elapsedTime)
            }
            is StopwatchState.Paused -> oldState
        }
}

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}

internal class TimestampMillisecondsFormatter() {

    companion object {
        const val DEFAULT_TIME = "00:00:000"
    }

    fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).pad(2)
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatted = (minutes / 60).pad(2)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')
}

internal class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) {

    var currentState: StopwatchState = StopwatchState.Paused(0)
        private set

    fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }
}

interface TimestampProvider {

    fun getMilliseconds(): Long
}*/
//////////////////////////// End Stopwatch code //////////////////////////