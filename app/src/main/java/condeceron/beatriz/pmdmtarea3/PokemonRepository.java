package condeceron.beatriz.pmdmtarea3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository {

    private ApiService apiService;

    public PokemonRepository() {
        apiService = RetrofitClient.getInstance();
    }

    public LiveData<Pokemon> getPokemonByName(String name) {
        MutableLiveData<Pokemon> data = new MutableLiveData<>();

        apiService.getPokemonByName(name).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    PokemonResponse pokemonResponse = response.body();

                    String image = pokemonResponse.getSprites().getFrontDefault();
                    String pokemonName = pokemonResponse.getName();
                    String index = String.valueOf(pokemonResponse.getId());
                    String height = String.valueOf(pokemonResponse.getHeight());
                    String weight = String.valueOf(pokemonResponse.getWeight());

                    String firstType = pokemonResponse.getTypes().get(0).getType().getName();
                    String secondType = pokemonResponse.getTypes().size() > 1 ? pokemonResponse.getTypes().get(1).getType().getName() : null;

                    Pokemon pokemon = new Pokemon(image, pokemonName, index, firstType, secondType, height, weight);

                    data.setValue(pokemon);
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}