package com.example.won_lim_a2


import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class MyListAdapter (private val context: Activity, private val playerId: Array<String>,
                     private val playerName: Array<String>, private val playerPosition: Array<String>
                     , private val playerGoals: Array<String>)

    : ArrayAdapter<String>(context, R.layout.player_list, playerName) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.player_list, null, true)

        val nameText = rowView.findViewById(R.id.nameTextView) as TextView
        val positionText = rowView.findViewById(R.id.positionTextView) as TextView
        val goalText = rowView.findViewById(R.id.goalTextView) as TextView

        nameText.text = "Name: ${playerName[position]}"
        positionText.text = "Position: ${playerPosition[position]}"
        goalText.text = "Goals: ${playerGoals[position]}"
        return rowView
    }
}