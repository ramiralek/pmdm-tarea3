package condeceron.beatriz.pmdmtarea3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import condeceron.beatriz.pmdmtarea3.databinding.FragmentPokedexBinding;


public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding;
    private PokedexViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPokedexBinding.inflate(inflater, container, false);
        binding.pokedexRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(this).get(PokedexViewModel.class);
        viewModel.getPokedex().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemonList) {
                if (pokemonList != null) {
                    binding.pokedexRecyclerview.setAdapter(new PokedexRecyclerViewAdapter((ArrayList<Pokemon>) pokemonList, getActivity()));
                }
            }
        });

        return binding.getRoot();
    }

    public void catchPokemon(Pokemon currentPokemon) {

    }

}