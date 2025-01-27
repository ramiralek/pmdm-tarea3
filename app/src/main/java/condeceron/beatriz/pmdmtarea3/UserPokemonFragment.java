package condeceron.beatriz.pmdmtarea3;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import condeceron.beatriz.pmdmtarea3.databinding.FragmentUserPokemonBinding;


public class UserPokemonFragment extends Fragment {

    private FragmentUserPokemonBinding binding;
    private ArrayList<Pokemon> pokemonList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUserPokemonBinding.inflate(inflater, container, false);
        pokemonList = new ArrayList<>();

        // START TODO
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean deletePokemonEnabled = sharedPreferences.getBoolean("delete_pokemon", false);
        System.out.println(deletePokemonEnabled);
        String languageSelected = sharedPreferences.getString("language", "");
        System.out.println(languageSelected);
        // END TODO

        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users")
                .document(userUid)
                .collection("pokemon")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot result) {
                        for (DocumentSnapshot document : result) {
                            String image = document.getString("image");
                            String name = document.getString("name");
                            String index = document.getString("index");
                            String firstType = document.getString("firstType");
                            String secondType = document.getString("secondType");
                            String height = document.getString("height");
                            String weight = document.getString("weight");

                            Pokemon pokemon;
                            if (secondType != null) {
                                pokemon = new Pokemon(image, name, index, firstType, secondType, height, weight);
                            } else {
                                pokemon = new Pokemon(image, name, index, firstType, height, weight);
                            }
                            pokemonList.add(pokemon);

                            binding.userPokemonRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                            binding.userPokemonRecyclerview.setAdapter(new UserPokemonRecyclerViewAdapter(pokemonList, getContext()));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        System.out.println("Error al obtener los Pokémon" + e);
                    }
                });

        return binding.getRoot();
    }

}