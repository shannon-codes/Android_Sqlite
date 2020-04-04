package com.example.won_lim_a2

class PlayerModelClass {

    var playerId: Int =0
    var playerName: String = ""
    var playerPosition: String =""
    var playerGoals: Int =0

    constructor(playerId: Int, playerName: String, playerPosition: String, playerGoals: Int) {
        this.playerId = playerId
        this.playerName = playerName
        this.playerPosition = playerPosition
        this.playerGoals = playerGoals
    }

    constructor(playerName: String, playerPosition: String, playerGoals: Int) {
        this.playerName = playerName
        this.playerPosition = playerPosition
        this.playerGoals = playerGoals
    }


}