package com.example.mvvm_example.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm_example.dataBase.Player;
import com.example.mvvm_example.repositries.Repo;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Player>> mPlayers;
    private  Repo repo;


    public MainViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);

    }

    public void insertPlayer(Player player) {
        repo.insertPlayer(player);

    }


    public LiveData<List<Player>> getPlayers() {
        mPlayers = repo.loadPlayers();
        return mPlayers;

    }

    public void deletePlayer(Player player) {
        repo.deletePlayer(player);

    }
}
