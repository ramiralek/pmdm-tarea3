package condeceron.beatriz.pmdmtarea3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon?offset=0&limit=150")
    Call<PokedexResponse> getPokedex();

    @GET("pokemon/{name}")
    Call<PokemonResponse> getPokemonByName(@Path("name") String name);

}
