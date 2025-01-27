package condeceron.beatriz.pmdmtarea3;


import androidx.recyclerview.widget.RecyclerView;

import condeceron.beatriz.pmdmtarea3.databinding.PokedexCardviewBinding;

public class PokedexViewHolder extends RecyclerView.ViewHolder {

    private final PokedexCardviewBinding binding;

    public PokedexViewHolder(PokedexCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Pokemon pokemon) {
        String name = pokemon.getName();
        binding.name.setText(name.substring(0, 1).toUpperCase() + name.substring(1));
        binding.executePendingBindings();
    }

}