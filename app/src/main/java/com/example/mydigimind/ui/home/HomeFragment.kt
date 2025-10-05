package com.example.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mydigimind.R
import com.example.mydigimind.ui.Task

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    companion object {
        val tasks = ArrayList<Task>()
        var adaptador: AdaptadorTareas? = null
        var first = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (first) {
            fillTask()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)
        val gridView: GridView = root.findViewById(R.id.gridView)
        gridView.adapter = adaptador

        return root
    }

    private fun fillTask() {
        tasks.add(Task("Practice1", arrayListOf("Monday", "Sunday"), "17:30"))
        tasks.add(Task("Practice2", arrayListOf("Monday"), "9:00"))
        tasks.add(Task("Practice3", arrayListOf("Saturday"), "16:00"))
        tasks.add(Task("Practice4", arrayListOf("Sunday"), "1:00"))
        tasks.add(Task("Practice5", arrayListOf("Friday"), "7:00"))
        tasks.add(Task("Practice6", arrayListOf("Wednesday"), "21:00"))
    }

    class AdaptadorTareas(private val contexto: Context, private val tasks: ArrayList<Task>) : BaseAdapter() {

        override fun getCount(): Int = tasks.size

        override fun getItem(position: Int): Any = tasks[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val vista: View
            val task = tasks[position]

            vista = convertView ?: LayoutInflater.from(contexto).inflate(R.layout.task_view, parent, false)

            val tv_title: TextView = vista.findViewById(R.id.tv_title)
            val tv_time: TextView = vista.findViewById(R.id.tv_time)
            val tv_days: TextView = vista.findViewById(R.id.tv_days)

            tv_title.text = task.title
            tv_time.text = task.time
            tv_days.text = task.days.joinToString(", ")

            return vista
        }
    }
}
