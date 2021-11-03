package com.example.balldontlieapplication.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.balldontlieapplication.model.PlayersModel;
import com.example.balldontlieapplication.view.network.APIServicePlayers;
import com.example.balldontlieapplication.view.network.RetroInstancePlayers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersListViewModel extends ViewModel {

    private MutableLiveData<List<PlayersModel>> playersList;

    public PlayersListViewModel(){
        playersList = new MutableLiveData<>();
    }

    public MutableLiveData<List<PlayersModel>> getPlayersListObserver() {
        return playersList;
    }

    public void makeApiCall() {
        APIServicePlayers apiService = RetroInstancePlayers.getRetroClient().create(APIServicePlayers.class);
        Call<List<PlayersModel>> call = apiService.getPlayerList();
        call.enqueue(new Callback<List<PlayersModel>>() {
            @Override
            public void onResponse(Call<List<PlayersModel>> call, Response<List<PlayersModel>> response) {
                playersList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PlayersModel>> call, Throwable t) {
                playersList.postValue(null);
            }
        });
    }
}