package com.github.ranaice.bakingapp.data.network;

import retrofit2.Retrofit;

@SuppressWarnings("unused")
public class MainService {

    private final Retrofit retrofit;

    public MainService() {
        retrofit = RetrofitClient.getClient();
    }

    public RecipeService getRecipeService() {
        return retrofit.create(RecipeService.class);
    }
}
