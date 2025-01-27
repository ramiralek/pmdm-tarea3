package condeceron.beatriz.pmdmtarea3;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.Preference;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import condeceron.beatriz.pmdmtarea3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
            NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
            NavigationUI.setupActionBarWithNavController(this, navController);
        }
        initializeAppBar();
        binding.bottomNavigationView.setOnItemSelectedListener(this::selectedBottomMenu);
    }

    private boolean selectedBottomMenu(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.pokedex_menu) {
            navController.navigate(R.id.pokedexFragment);
        } else if (menuItem.getItemId() == R.id.user_pokemon_menu) {
            navController.navigate(R.id.userPokemonFragment);
        } else if (menuItem.getItemId() == R.id.settings_menu) {
            navController.navigate(R.id.settingsFragment);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp() || navController.navigateUp();
    }

    private void initializeAppBar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.pokedexFragment,
                R.id.userPokemonFragment,
                R.id.settingsFragment
        ).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    public void catchPokemon(Pokemon currentPokemon) {
        Toast.makeText(this, R.string.pokemon_added,
                Toast.LENGTH_SHORT).show();

        PokedexViewModel viewModel = new PokedexViewModel();
        viewModel = new ViewModelProvider(this).get(PokedexViewModel.class);
        viewModel.getPokemonByName(currentPokemon.getName()).observe(this, new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                if (pokemon != null) {
                    String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    db.collection("users")
                            .document(userUid)
                            .collection("pokemon")
                            .add(createPokemonMap(pokemon));

                }
            }
        });

    }

    private Map<String, Object> createPokemonMap(Pokemon pokemon) {
        Map<String, Object> pokemonData = new HashMap<>();
        pokemonData.put("image", pokemon.getImage());
        pokemonData.put("name", pokemon.getName());
        pokemonData.put("index", pokemon.getIndex());
        pokemonData.put("firstType", pokemon.getFirstType());
        pokemonData.put("secondType", pokemon.getSecondType());
        pokemonData.put("height", pokemon.getHeight());
        pokemonData.put("weight", pokemon.getWeight());
        return pokemonData;
    }

}