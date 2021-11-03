package com.example.balldontlieapplication.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.balldontlieapplication.model.PlayersModel;
import com.example.balldontlieapplication.model.TeamsModel;
import com.example.balldontlieapplication.view.network.APIServicePlayers;
import com.example.balldontlieapplication.view.network.APIServiceTeams;
import com.example.balldontlieapplication.view.network.RetroInstancePlayers;
import com.example.balldontlieapplication.view.network.RetroInstanceTeams;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsViewModel extends ViewModel {

    private MutableLiveData<List<TeamsModel>> teamsList;

    public TeamsViewModel() {
        teamsList = new MutableLiveData<>();
    }

    public MutableLiveData<List<TeamsModel>> getTeamsListObserver() {
        return teamsList;

    }

    public void makeApiCall() {
        APIServiceTeams apiService = RetroInstanceTeams.getRetroClient().create(APIServiceTeams.class);
        Call<List<TeamsModel>> call = apiService.getTeamsList();
        call.enqueue(new Callback<List<TeamsModel>>() {
            @Override
            public void onResponse(Call<List<TeamsModel>> call, Response<List<TeamsModel>> response) {
                teamsList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TeamsModel>> call, Throwable t) {
                teamsList.postValue(null);
            }
        });
    }
}