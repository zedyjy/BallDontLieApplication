package com.example.balldontlieapplication.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.balldontlieapplication.R;
import com.example.balldontlieapplication.model.TeamsModel;

import java.util.List;

    public class TeamsListAdapter  extends RecyclerView.Adapter<TeamsListAdapter.MyViewHolder> {
        private Context context;
        private List<TeamsModel> teamsList;
        private ItemClickListener clickListener;

        public TeamsListAdapter(Context context, List<TeamsModel> teamsList, ItemClickListener clickListener) {
            this.context = context;
            this.teamsList = teamsList;
            this.clickListener = clickListener;
        }

        public void setTeamsList(List<TeamsModel> teamsList) {
            this.teamsList = teamsList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public TeamsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.fragment_dashboard, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TeamsListAdapter.MyViewHolder holder, int position) {
            holder.tvTitle.setText(this.teamsList.get(position).getFull_name());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onTeamsClick(teamsList.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            if(this.teamsList != null) {
                return this.teamsList.size();
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
            public void onTeamsClick(TeamsModel teams);
        }
    }

