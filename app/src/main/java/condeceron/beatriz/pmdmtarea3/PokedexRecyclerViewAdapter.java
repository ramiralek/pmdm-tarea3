package condeceron.beatriz.pmdmtarea3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import condeceron.beatriz.pmdmtarea3.databinding.PokedexCardviewBinding;

public class PokedexRecyclerViewAdapter extends RecyclerView.Adapter<PokedexViewHolder> {

    private final ArrayList<Pokemon> pokemon;
    private FragmentActivity fragmentActivity;

    public PokedexRecyclerViewAdapter(ArrayList<Pokemon> pokemon, FragmentActivity activity) {
        this.pokemon = pokemon;
        this.fragmentActivity = activity;
    }

    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokedexCardviewBinding binding = PokedexCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new PokedexViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        Pokemon currentPokemon = this.pokemon.get(position);
        holder.bind(currentPokemon);
        holder.itemView.setOnClickListener(view -> {
            ((MainActivity) fragmentActivity).catchPokemon(currentPokemon);
        });
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

}
