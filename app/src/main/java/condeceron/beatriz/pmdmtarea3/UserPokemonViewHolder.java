package condeceron.beatriz.pmdmtarea3;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;

import condeceron.beatriz.pmdmtarea3.databinding.UserPokemonCardviewBinding;

public class UserPokemonViewHolder extends RecyclerView.ViewHolder {

    private final UserPokemonCardviewBinding binding;

    public UserPokemonViewHolder(UserPokemonCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Pokemon pokemon) {
        binding.name.setText(pokemon.getName());
        binding.type.setText(String.format("%s %s", pokemon.getFirstType(), pokemon.getSecondType()));
        // TODO binding.image.setText(pokemon.getImage());
        binding.executePendingBindings();
    }

}
