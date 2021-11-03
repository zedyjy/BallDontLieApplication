package com.example.balldontlieapplication.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.balldontlieapplication.R;
import com.example.balldontlieapplication.model.PlayersModel;
import com.example.balldontlieapplication.model.TeamsModel;

import java.util.List;

public class PlayerListAdapter  extends RecyclerView.Adapter<PlayerListAdapter.MyViewHolder> {
    private Context context;
    private List<PlayersModel> playersList;
    private ItemClickListener clickListener;

    public PlayerListAdapter(Context context, List<PlayersModel> movieList, ItemClickListener clickListener) {
        this.context = context;
        this.playersList = movieList;
        this.clickListener = clickListener;
    }

    public void setPlayersList(List<PlayersModel> playersList) {
        this.playersList = playersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.playersList.get(position).getFirst_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onPlayerClick(playersList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.playersList != null) {
            return this.playersList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.titleView);

        }
    }
    public interface ItemClickListener{
        public void onPlayerClick(PlayersModel player);

        void onTeamClick(TeamsModel teams);
    }
}