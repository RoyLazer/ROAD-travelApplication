package com.rone.road

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class explorerAdapter ( var todos: List<explorerTodo>): RecyclerView.Adapter<explorerAdapter.TodoViewHolder>(){

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.textTitle).text = todos[position].title
            findViewById<TextView>(R.id.leaveText).text = todos[position].leave
            findViewById<TextView>(R.id.daysText).text = todos[position].days
            findViewById<TextView>(R.id.prizeText).text = todos[position].prize
            findViewById<TextView>(R.id.peopleText).text = todos[position].people
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}