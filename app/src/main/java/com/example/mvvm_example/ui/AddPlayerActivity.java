package com.example.mvvm_example.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvvm_example.dataBase.Player;
import com.example.mvvm_example.R;
import com.example.mvvm_example.viewModels.MainViewModel;

public class AddPlayerActivity extends AppCompatActivity {

    private EditText mNameEditText, mTeamEditText;

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
    }

    public void savePlayer(View view) {

        String name = mNameEditText.getText().toString();
        String team = mTeamEditText.getText().toString();
        Player player = new Player(name, team);


        if (name.trim().isEmpty() || team.trim().isEmpty()) {
            Toast.makeText(this, "please insert a name and team", Toast.LENGTH_SHORT).show();
        }else {

            //get from viewModel
            MainViewModel mainViewModel = new MainViewModel(getApplication());
            mainViewModel.insertPlayer(player);
            Toast.makeText(this, "item saved", Toast.LENGTH_SHORT).show();
            finish();
        }

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
