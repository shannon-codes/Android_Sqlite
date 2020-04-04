package com.example.won_lim_a2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private val DATABASE_NAME = "HockeyDatabase"
        private val DATABASE_VERSION = 1
        private val TABLE_PLAYERS = "PlayerTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_POSITION = "position"
        private val KEY_GOALS = "goals"
    }

    override fun onCreate(db: SQLiteDatabase?) {
    val CREATE_PLAYERS_TABLE = ("CREATE TABLE " + TABLE_PLAYERS + " ("+ KEY_ID+ " INTEGER PRIMARY KEY, " + KEY_NAME
        + " TEXT, "+ KEY_POSITION +" TEXT, "+ KEY_GOALS+" INTEGER"+" )")

        db?.execSQL(CREATE_PLAYERS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db!!.execSQL("DROP TABLE IF EXISTS "+ TABLE_PLAYERS)
        onCreate(db)

    }

    fun addPlayer(player: PlayerModelClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, player.playerName)
        contentValues.put(KEY_POSITION, player.playerPosition)
        contentValues.put(KEY_GOALS, player.playerGoals)

        val success = db.insert(TABLE_PLAYERS, null, contentValues)

        db.close()

        return success
    }

}