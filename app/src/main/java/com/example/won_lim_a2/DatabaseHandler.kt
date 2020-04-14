package com.example.won_lim_a2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor

import android.database.sqlite.SQLiteException



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

    fun viewPlayer():List<PlayerModelClass>
    {
        val playerList:ArrayList<PlayerModelClass> = ArrayList<PlayerModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_PLAYERS"
        val db = this.readableDatabase

        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var playerId: Int
        var playerName: String
        var playerPosition: String
        var playerGoals : Int

        if (cursor.moveToFirst()) {
            do {
                playerId = cursor.getInt(cursor.getColumnIndex("id"))
                playerName = cursor.getString(cursor.getColumnIndex("name"))
                playerPosition = cursor.getString(cursor.getColumnIndex("position"))
                playerGoals = cursor.getInt(cursor.getColumnIndex("goals"))

                val player= PlayerModelClass(playerId = playerId, playerName = playerName,
                        playerPosition = playerPosition, playerGoals = playerGoals)
                playerList.add(player)
            } while (cursor.moveToNext())
        }
        return playerList
    }




}