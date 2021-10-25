package com.p17191.ergasies.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create button on click functionality
        button1.setOnClickListener { view ->
            SelectDate(view);
        }
    }

    fun SelectDate(view : View){

        //get calendar instance to get year, month and day
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR);
        val month = calendar.get(Calendar.MONTH);
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog (this,
            DatePickerDialog.OnDateSetListener {view,selectedYear,selectedMonth,selectedDayOfMonth ->
                //code here gets executed after we press ok during the dialog

                //we do month + 1 as months starts from 00
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear";
                textViewSelectedDate.text = selectedDate;

                //get selected date in "date" format, so we can do calculations with it
                val sdf =  SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val formattedDate = sdf.parse(selectedDate)

                val timeInSeconds = (calendar.timeInMillis - formattedDate.time) / 60000 //get minutes
                textViewAgeInMinutes.text = timeInSeconds.toString();

            },
            year,
            month,
            dayOfMonth).show();
    }
}