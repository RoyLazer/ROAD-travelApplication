package com.rone.road

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class explorerAdapter (var todos: List<explorerTodo>): RecyclerView.Adapter<explorerAdapter.TodoViewHolder>(){



    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var currentPosition: Int = 0
        val textTitle = itemView.findViewById<TextView>(R.id.textTitle)
        val leaveText = itemView.findViewById<TextView>(R.id.leaveText)
        val daysText = itemView.findViewById<TextView>(R.id.daysText)
        val prizeText = itemView.findViewById<TextView>(R.id.prizeText)
        val peopleText = itemView.findViewById<TextView>(R.id.peopleText)
        val IdText = itemView.findViewById<TextView>(R.id.IdText)
        init{
            itemView.setOnClickListener{
             val intent = Intent(itemView.context, listingActivity::class.java)
                intent.putExtra("travelPosition", currentPosition)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return TodoViewHolder(view)
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val travel = todos[position]
        holder.textTitle.text = travel.title
        holder.leaveText.text = travel.leave
        holder.daysText.text = travel.days
        holder.prizeText.text = travel.prize
        holder.peopleText.text = travel.people
        holder.IdText.text = travel.id.toString()
        holder.currentPosition = position
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    class travelListener (val clickListener: (travelId: Int) -> Unit){
        fun onClick(travel: explorerTodo) = clickListener(travel.id)
    }



}