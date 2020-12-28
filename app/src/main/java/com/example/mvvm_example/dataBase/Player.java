package com.example.mvvm_example.dataBase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_data")
public class Player {


    @PrimaryKey(autoGenerate = true)
    private int playerId;
    private String playerName;
    private String playerTeam;


    public Player(int playerId, String playerName, String playerTeam) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerTeam = playerTeam;
    }

    @Ignore
    public Player(String playerName, String playerTeam) {
        this.playerName = playerName;
        this.playerTeam = playerTeam;
    }


    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(String playerTeam) {
        this.playerTeam = playerTeam;
    }
}
