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
    private Repo mRepo;


    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepo = new Repo(application);

    }


    public LiveData<List<Player>> getPlayers() {
        mPlayers = mRepo.loadPlayers();
        return mPlayers;

    }

    public void insertPlayer(Player player) {
        mRepo.insertPlayer(player);

    }

    public void deletePlayer(Player player) {
        mRepo.deletePlayer(player);

    }

    public void deleteAllPlayers() {
        mRepo.deleteAllPlayers();
    }

    public void updatePlayer(Player player) {
        mRepo.updatePlayer(player);
    }
}
