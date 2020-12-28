package com.example.mvvm_example.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mvvm_example.dataBase.Player;
import com.example.mvvm_example.PlayerAdapter;
import com.example.mvvm_example.R;
import com.example.mvvm_example.viewModels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private PlayerAdapter mPlayerAdapter;
    private MainViewModel mMainViewModel;
    private LiveData<List<Player>> mPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mPlayers = mMainViewModel.getPlayers();
        mPlayers.observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                Log.d(TAG, "onChanged called ");
                mPlayerAdapter.setPlayers(players);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Player player = mPlayerAdapter.getPlayerAt(position);
                mMainViewModel.deletePlayer(player);
                Toast.makeText(MainActivity.this, "item deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mPlayerAdapter = new PlayerAdapter();
        mRecyclerView.setAdapter(mPlayerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemClicked = item.getItemId();
        if (itemClicked == R.id.Add_player_action) {
            Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
