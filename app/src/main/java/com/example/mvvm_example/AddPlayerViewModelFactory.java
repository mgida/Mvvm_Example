package com.example.mvvm_example;



import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_example.dataBase.PlayerDataBase;
import com.example.mvvm_example.viewModels.AddEditPlayerViewModel;

public class AddPlayerViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private PlayerDataBase mDb;
    private int mPlayerId;

    public AddPlayerViewModelFactory(PlayerDataBase mDb, int mPlayerId) {
        this.mDb = mDb;
        this.mPlayerId = mPlayerId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddEditPlayerViewModel(mDb, mPlayerId);
    }
}
