package com.example.habit_start.ui.habits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habit_start.MainActivity
import com.example.habit_start.R
import com.example.habit_start.ui.dashboard.DashboardViewModel
import com.example.habit_start.ui.datatypes.Habit
import com.example.habit_start.ui.datatypes.datesOfWeek
import java.security.SecureRandom
import kotlin.random.Random

/**
 * Holds a recycler view of user's habits, or a filtered version of it
 * Uses global 'sharedHabits' to populate recycler view
 */
class HabitsFragment : Fragment(), HabitRecyclerViewAdapter.OnRecyclerItemClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_habit, container, false)

        //recycler view population and linking
        val recyclerView = root.findViewById<RecyclerView>(R.id.fragment_habit_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = HabitRecyclerViewAdapter((activity as MainActivity).sharedHabits, this)
        recyclerView.adapter = adapter
        return root
    }

    /**
     * On the recycler item click go to HabitActiveFragment view with the selected Habit details
     * Uses global 'sharedHabits' array
     */
    override fun onItemClick(position: Int) {
        //set global current habit variable
        val habit = (activity as MainActivity).sharedHabits[position]
        val nextFragment = HabitActiveFragment()
        val bundle = Bundle()
        bundle.putString("name", habit.name)    //one of these is obsolete, still deciding
        bundle.putInt("position", position)
        nextFragment.arguments = bundle
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_habit_layout, nextFragment, "habitActiveFragment")
            .addToBackStack(null)
            .commit()
    }



    //If I need another view inside of fragment
    /*private lateinit var habitViewModel: Fragment(), View.OnClickListener,

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        habitViewModel =
            ViewModelProviders.of(this).get(HabitViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_habit, container, false)
        val textView: TextView = root.findViewById(R.id.text_habit)
        habitViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }*/
}