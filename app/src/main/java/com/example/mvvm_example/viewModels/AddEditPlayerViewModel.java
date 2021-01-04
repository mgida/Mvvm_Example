package com.example.mvvm_example.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_example.dataBase.Player;
import com.example.mvvm_example.dataBase.PlayerDataBase;
import com.example.mvvm_example.repositries.Repo;

public class AddEditPlayerViewModel extends ViewModel {

    LiveData<Player> mPlayer;

    public AddEditPlayerViewModel(PlayerDataBase mDb, int mPlayerId) {
        mPlayer = mDb.playerDao().loadPlayerById(mPlayerId);
    }

    public LiveData<Player> getPlayer() {
        return mPlayer;
    }


}
