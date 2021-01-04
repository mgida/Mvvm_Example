package com.example.mvvm_example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_example.dataBase.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> mPlayers = new ArrayList<>();
    private onItemClickListener onItemClickListener;

    public PlayerAdapter(PlayerAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item_player, parent, false);

        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.mPlayerNameTextView.setText(mPlayers.get(position).getPlayerName());
        holder.mPlayerTeamTextView.setText(mPlayers.get(position).getPlayerTeam());

    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }


    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mPlayerNameTextView;
        private TextView mPlayerTeamTextView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            mPlayerNameTextView = itemView.findViewById(R.id.player_name_tv);
            mPlayerTeamTextView = itemView.findViewById(R.id.player_team_tv);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            int playerId = mPlayers.get(getAdapterPosition()).getPlayerId();
            onItemClickListener.onItemClicked(playerId);
        }
    }

    public interface onItemClickListener {
        public void onItemClicked(int playerId);
    }


    public void setPlayers(List<Player> players) {
        mPlayers = players;
        notifyDataSetChanged();

    }

    public Player getPlayerAt(int position) {
        return mPlayers.get(position);

    }


}
