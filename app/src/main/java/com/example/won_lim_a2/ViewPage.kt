package com.example.won_lim_a2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*

class ViewPage: AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val player: List<PlayerModelClass> = databaseHandler.viewPlayer()
        val playerArrayId = Array<String>(player.size) { "0" }
        val playerArrayName = Array<String>(player.size) { "null" }
        val playerArrayPosition = Array<String>(player.size) { "null" }
        val playerArrayGoals = Array<String>(player.size) { "0" }

        var index = 0
        for (e in player) {
            playerArrayId[index] = e.playerId.toString()
            playerArrayName[index] = e.playerName
            playerArrayPosition[index] = e.playerPosition
            playerArrayGoals[index] = e.playerGoals.toString()
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(this, playerArrayId, playerArrayName, playerArrayPosition, playerArrayGoals)
        listView.adapter = myListAdapter
    }
}

