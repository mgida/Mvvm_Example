package com.example.mvvm_example.repositries;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvm_example.AppExecutors;
import com.example.mvvm_example.dataBase.Player;
import com.example.mvvm_example.dataBase.PlayerDataBase;

import java.util.List;

public class Repo {

    private PlayerDataBase mDb;
    private LiveData<List<Player>> mPlayers;


    public Repo(Application application) {
        mDb = PlayerDataBase.getInstance(application.getApplicationContext());
    }

    public void insertPlayer(final Player player) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.playerDao().insertPlayer(player);
            }
        });

    }

    public void deletePlayer(final Player player) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.playerDao().deletePlayer(player);
            }
        });
    }


    public LiveData<List<Player>> loadPlayers() {
        mPlayers = mDb.playerDao().loadPlayers();
        return mPlayers;
    }


}
