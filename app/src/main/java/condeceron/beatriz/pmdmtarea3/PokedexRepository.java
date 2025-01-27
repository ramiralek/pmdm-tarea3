package condeceron.beatriz.pmdmtarea3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexRepository {

    private ApiService apiService;

    public PokedexRepository() {
        apiService = RetrofitClient.getInstance();
    }

    public LiveData<List<Pokemon>> getPokedex() {
        MutableLiveData<List<Pokemon>> data = new MutableLiveData<>();

        apiService.getPokedex().enqueue(new Callback<PokedexResponse>() {
            @Override
            public void onResponse(Call<PokedexResponse> call, Response<PokedexResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body().getResults());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<PokedexResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}