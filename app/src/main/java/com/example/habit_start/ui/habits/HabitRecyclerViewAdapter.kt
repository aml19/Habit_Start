package com.example.habit_start.ui.habits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habit_start.R
import com.example.habit_start.ui.datatypes.Habit

class HabitRecyclerViewAdapter(habits : Array<Habit>, onRecyclerItemClick : OnRecyclerItemClick) : RecyclerView.Adapter<HabitRecyclerViewAdapter.ViewHolder>() {
    private val mOnItemClick : OnRecyclerItemClick = onRecyclerItemClick
    private val mHabits = habits

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.habit_card_layout, parent, false)
        return ViewHolder(v, mOnItemClick)
    }

    override fun getItemCount(): Int {
        return mHabits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mHabits[position])
    }

    class ViewHolder(itemView : View, onRecyclerItemClick: OnRecyclerItemClick) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val onItemClick : OnRecyclerItemClick
        init {
            itemView.setOnClickListener(this)
            this.onItemClick = onRecyclerItemClick
        }

        //sets the value for all the list items
        fun bindItems(habit : Habit) {
            val nameTextView = itemView.findViewById<TextView>(R.id.habit_card_name)
            nameTextView.text = habit.name
            val goalTime = itemView.findViewById<TextView>(R.id.habit_card_goalTime)
            goalTime.text = habit.goalTime.toString()
            val activeTime = itemView.findViewById<TextView>(R.id.habit_card_activeTime)
            activeTime.text = habit.activeTime.toString()
        }

        //set onClick listener
        override fun onClick(v: View?) {
            onItemClick.onItemClick(adapterPosition)
        }
    }

    interface OnRecyclerItemClick{
        fun onItemClick(position:Int)
    }
}