package com.example.balldontlieapplication.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.balldontlieapplication.R;
import com.example.balldontlieapplication.model.PlayersModel;
import com.example.balldontlieapplication.model.TeamsModel;
import com.example.balldontlieapplication.view.adapters.PlayerListAdapter;
import com.example.balldontlieapplication.view.adapters.TeamsListAdapter;
import com.example.balldontlieapplication.viewModel.PlayersListViewModel;
import com.example.balldontlieapplication.viewModel.TeamsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.balldontlieapplication.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity implements PlayerListAdapter.ItemClickListener {

    private ActivityMainBinding binding;

    private List<PlayersModel> playersModelList;
    private List<TeamsModel> teamsModelList;
    private PlayerListAdapter adapter;
    private TeamsListAdapter teamsListAdapter;
    private PlayersListViewModel viewModel;
    private TeamsViewModel teamsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        RecyclerView recyclerView = findViewById(R.id.players);
        final TextView noresult = findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new PlayerListAdapter(this, playersModelList, (PlayerListAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PlayersListViewModel.class);
        viewModel.getPlayersListObserver().observe(this, new Observer<List<PlayersModel>>() {

            @Override
            public void onChanged(List<PlayersModel> playersModels) {
                if(playersModels != null) {
                    playersModelList = playersModels;
                    adapter.setPlayersList(playersModels);
                    noresult.setVisibility(View.GONE);
                }
            }
        });

        RecyclerView recyclerView2 = findViewById(R.id.teams);
        final TextView noresult2 = findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager2 = new GridLayoutManager(this, 2);
        recyclerView2.setLayoutManager(layoutManager2);
        //teamsListAdapter =  new TeamsListAdapter(this, teamsModelList, (TeamsListAdapter.ItemClickListener) this);
        recyclerView2.setAdapter(teamsListAdapter);

        teamsViewModel = ViewModelProviders.of(this).get(TeamsViewModel.class);
        /*teamsViewModel.getTeamsListObserver().observe(this, new Observer<List<TeamsViewModel>>() {

            @Override
            public void onChanged(List<TeamsModel> teamsModels) {
                if(teamsModels != null) {
                    teamsModelList = teamsModels;
                    teamsListAdapter.setTeamsList(teamsModels);
                    noresult.setVisibility(View.GONE);
                }
            }
        });*/

        viewModel.makeApiCall();
        teamsViewModel.makeApiCall();
    }

    @Override
    public void onPlayerClick(PlayersModel players) {
        Toast.makeText(this, "Clicked Players Name is : " +players.getFirst_name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTeamClick(TeamsModel teams) {
        Toast.makeText(this, "Clicked Teams Name is : " +teams.getFull_name(), Toast.LENGTH_SHORT).show();
    }
}