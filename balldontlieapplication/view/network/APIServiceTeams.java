package com.example.balldontlieapplication.view.network;

import com.example.balldontlieapplication.model.TeamsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServiceTeams {

    @GET("https://www.balldontlie.io/api/v1/teams")
    Call<List<TeamsModel>> getTeamsList();
}
