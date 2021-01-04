package com.example.mvvm_example.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvm_example.AddPlayerViewModelFactory;
import com.example.mvvm_example.dataBase.Player;
import com.example.mvvm_example.R;
import com.example.mvvm_example.dataBase.PlayerDataBase;
import com.example.mvvm_example.viewModels.AddEditPlayerViewModel;
import com.example.mvvm_example.viewModels.MainViewModel;

public class AddEditPlayerActivity extends AppCompatActivity {
    public static final String PLAYER_ID_KEY = "playerId";
    public static final int PLAYER_ID_DEFAULT_VALUE = -1;


    private EditText mNameEditText, mTeamEditText;
    private Button mButton;
    private LiveData<Player> mPlayer;
    private PlayerDataBase mDb;
    private int mPlayerId = PLAYER_ID_DEFAULT_VALUE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNameEditText = findViewById(R.id.name_et);
        mTeamEditText = findViewById(R.id.team_et);

        mButton = findViewById(R.id.button);
        mDb = PlayerDataBase.getInstance(getApplicationContext());


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PLAYER_ID_KEY)) {
            actionBar.setTitle("update item");
            mButton.setText("update");
            mPlayerId = intent.getIntExtra(PLAYER_ID_KEY, PLAYER_ID_DEFAULT_VALUE);
            AddPlayerViewModelFactory viewModelFactory = new AddPlayerViewModelFactory(mDb, mPlayerId);
            final AddEditPlayerViewModel addEditPlayerViewModel = ViewModelProviders.of(this, viewModelFactory)
                    .get(AddEditPlayerViewModel.class);

            addEditPlayerViewModel.getPlayer().observe(this, new Observer<Player>() {
                @Override
                public void onChanged(Player player) {
                    addEditPlayerViewModel.getPlayer().removeObserver(this);
                    loadDataOf(player);
                }
            });


        } else {
            actionBar.setTitle("insert item");

        }
    }

    private void loadDataOf(Player player) {
        mNameEditText.setText(player.getPlayerName());
        mTeamEditText.setText(player.getPlayerTeam());
    }

    public void savePlayer(View view) {

        String name = mNameEditText.getText().toString();
        String team = mTeamEditText.getText().toString();
        Player player = new Player(name, team);

        MainViewModel mainViewModel = new MainViewModel(getApplication());


        if (name.trim().isEmpty() || team.trim().isEmpty()) {
            Toast.makeText(this, "please insert a name and team", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mPlayerId == PLAYER_ID_DEFAULT_VALUE) {
            mainViewModel.insertPlayer(player);
            Toast.makeText(this, "item saved", Toast.LENGTH_SHORT).show();
        } else {
            //get from viewModel
            player.setPlayerId(mPlayerId);
            mainViewModel.updatePlayer(player);
            Toast.makeText(this, "item updated", Toast.LENGTH_SHORT).show();
        }


        finish();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
