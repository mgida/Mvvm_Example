package com.example.mvvm_example.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player_data ORDER BY playerId")
    LiveData<List<Player>> loadPlayers();

    @Insert
    void insertPlayer(Player player);

    @Delete
    void deletePlayer(Player player);

}
