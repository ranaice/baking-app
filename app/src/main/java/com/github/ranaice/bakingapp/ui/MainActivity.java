package com.github.ranaice.bakingapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.ranaice.bakingapp.R;
import com.github.ranaice.bakingapp.data.model.Recipe;
import com.github.ranaice.bakingapp.data.network.MainService;
import com.github.ranaice.bakingapp.ui.adapter.RecipeAdapter;
import com.github.ranaice.bakingapp.ui.decorator.SameMarginDecorator;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_recipes)
    RecyclerView rvRecipes;

    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fetchRecipes();

        setupRecyclerView();

    }

    private void setupRecyclerView() {
        rvRecipes.setHasFixedSize(true);
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));
        rvRecipes.addItemDecoration(new SameMarginDecorator((int) getResources().getDimension(R.dimen.space_16_dp)));

        recipeAdapter = new RecipeAdapter();
        rvRecipes.setAdapter(recipeAdapter);
    }

    public void fetchRecipes() {
        new MainService().getRecipeService().getRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                Log.d("MainActivity", response.body().toString());
                if (response.isSuccessful()) {
                    recipeAdapter.setRecipes(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Resposta obtida com ERRO", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", t.getLocalizedMessage());
                t.printStackTrace();
            }
        });
    }

}
