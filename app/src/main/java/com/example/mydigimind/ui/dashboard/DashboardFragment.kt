package com.example.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mydigimind.R
import com.example.mydigimind.ui.Task
import com.example.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Calendar

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Botón para seleccionar hora
        val btn_time: Button = root.findViewById(R.id.btn_time)
        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                root.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        // Referencias de elementos
        val btn_save: Button = root.findViewById(R.id.btn_save)
        val et_titulo: EditText = root.findViewById(R.id.et_task)
        val checkMonday: CheckBox = root.findViewById(R.id.checkMonday)
        val checkTuesday: CheckBox = root.findViewById(R.id.checkTuesday)
        val checkWednesday: CheckBox = root.findViewById(R.id.checkWednesday)
        val checkThursday: CheckBox = root.findViewById(R.id.checkThursday)
        val checkFriday: CheckBox = root.findViewById(R.id.checkFriday)
        val checkSaturday: CheckBox = root.findViewById(R.id.checkSaturday)
        val checkSunday: CheckBox = root.findViewById(R.id.checkSunday)

        // Guardar tarea
        btn_save.setOnClickListener {
            val title = et_titulo.text.toString()
            val time = btn_time.text.toString()
            val days = ArrayList<String>()

            if (checkMonday.isChecked) days.add("Monday")
            if (checkTuesday.isChecked) days.add("Tuesday")
            if (checkWednesday.isChecked) days.add("Wednesday")
            if (checkThursday.isChecked) days.add("Thursday")
            if (checkFriday.isChecked) days.add("Friday")
            if (checkSaturday.isChecked) days.add("Saturday")
            if (checkSunday.isChecked) days.add("Sunday")

            if(title.isNotEmpty() && days.isNotEmpty()) {
                val task = Task(title, days, time)
                HomeFragment.tasks.add(task) // Agrega a la lista compartida
                HomeFragment.adaptador?.notifyDataSetChanged() // Actualiza el GridView

                Toast.makeText(root.context, "New task added", Toast.LENGTH_SHORT).show()

                // Limpiar campos
                et_titulo.text.clear()
                btn_time.text = "Set Time"
                checkMonday.isChecked = false
                checkTuesday.isChecked = false
                checkWednesday.isChecked = false
                checkThursday.isChecked = false
                checkFriday.isChecked = false
                checkSaturday.isChecked = false
                checkSunday.isChecked = false
            } else {
                Toast.makeText(root.context, "Please enter title and select at least one day", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }
}
