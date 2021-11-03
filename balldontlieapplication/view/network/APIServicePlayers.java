package com.example.balldontlieapplication.view.network;

import com.example.balldontlieapplication.model.PlayersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServicePlayers {

    @GET("https://www.balldontlie.io/api/v1/players")
    Call<List<PlayersModel>> getPlayerList();
}