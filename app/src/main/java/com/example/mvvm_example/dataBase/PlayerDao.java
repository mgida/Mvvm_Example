package com.example.mvvm_example.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player_data ORDER BY playerId")
    LiveData<List<Player>> loadPlayers();

    @Insert
    void insertPlayer(Player player);

    @Delete
    void deletePlayer(Player player);

    @Query("DELETE FROM player_data")
    void deleteAllPlayers();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePlayer(Player player);

    @Query("SELECT * FROM player_data WHERE playerId = :id")
    LiveData<Player> loadPlayerById(int id);

}
