package com.rone.road.fragmentExplore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rone.road.R
import com.rone.road.R.layout.fragment_explore
import com.rone.road.explorerAdapter
import com.rone.road.explorerTodo

class exploreFragment : Fragment(){

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(fragment_explore, container, false)
        var todoList = mutableListOf(
            explorerTodo("Viaje a Cancun","4 personas", "4 días","1200xP","12 de Diciembre") ,
            explorerTodo("Viaje a Puebla","4 personas", "4 días","1200xP","12 de Diciembre"),
            explorerTodo("Viaje a EdoMex","4 personas", "4 días","1200xP","12 de Diciembre"),
            explorerTodo("Viaje a Oaxaca","4 personas", "4 días","1200xP","12 de Diciembre"),
            explorerTodo("Viaje a Yucatan","4 personas", "4 días","1200xP","12 de Diciembre")

        )
        val adapter = explorerAdapter(todoList)
        view.findViewById<RecyclerView>(R.id.rvExplorer).layoutManager = LinearLayoutManager(activity)
        view.findViewById<RecyclerView>(R.id.rvExplorer).adapter = adapter

    return view
    }
}
