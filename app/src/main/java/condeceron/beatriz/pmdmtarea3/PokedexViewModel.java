package condeceron.beatriz.pmdmtarea3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PokedexViewModel extends ViewModel {

    private PokedexRepository pokedexRepository;
    private LiveData<List<Pokemon>> pokemonList;
    private PokemonRepository pokemonRepository;
    private LiveData<Pokemon> pokemon;

    public PokedexViewModel() {
        pokedexRepository = new PokedexRepository();
        pokemonRepository = new PokemonRepository();
    }

    public LiveData<List<Pokemon>> getPokedex() {
        if (pokemonList == null) {
            pokemonList = pokedexRepository.getPokedex();
        }
        return pokemonList;
    }

    public LiveData<Pokemon> getPokemonByName(String name) {
        pokemon = pokemonRepository.getPokemonByName(name);
        return pokemon;
    }

}