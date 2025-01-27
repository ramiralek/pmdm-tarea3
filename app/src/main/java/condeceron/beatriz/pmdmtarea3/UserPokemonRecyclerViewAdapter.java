package condeceron.beatriz.pmdmtarea3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import condeceron.beatriz.pmdmtarea3.databinding.PokedexCardviewBinding;
import condeceron.beatriz.pmdmtarea3.databinding.UserPokemonCardviewBinding;

public class UserPokemonRecyclerViewAdapter extends RecyclerView.Adapter<UserPokemonViewHolder> {

    private final ArrayList<Pokemon> pokemon;
    private final Context context;

    public UserPokemonRecyclerViewAdapter(ArrayList<Pokemon> pokemon, Context context) {
        this.pokemon = pokemon;
        this.context = context;
    }

    @NonNull
    @Override
    public UserPokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserPokemonCardviewBinding binding = UserPokemonCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new UserPokemonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserPokemonViewHolder holder, int position) {
        Pokemon currentPokemon = this.pokemon.get(position);
        holder.bind(currentPokemon);
        holder.itemView.setOnClickListener(view -> {
            pokemonClicked(currentPokemon, view);
        });
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    private void pokemonClicked(Pokemon currentPokemon, View view) {
        Navigation.findNavController(view).navigate(R.id.pokemonDetailFragment);
    }

}
