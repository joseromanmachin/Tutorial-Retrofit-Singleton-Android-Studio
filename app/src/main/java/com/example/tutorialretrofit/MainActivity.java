package com.example.tutorialretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutorialretrofit.dataAcces.Api;
import com.example.tutorialretrofit.dataAcces.Estado;
import com.example.tutorialretrofit.dataAcces.RetrofitInstace;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarElmentos();
        llamarApi();
    }

    private void llamarApi() {
        Call<List<Estado>> call = api.getEstados();
        call.enqueue(new Callback<List<Estado>>() {
            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                if (response.isSuccessful()){
                    for (int i = 0; i <response.body().size() ; i++) {
                        Estado estado = response.body().get(i);
                        textView.append("\n"+ estado.getClave() + " -- " + estado.getNombre());
                    }
                }else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializarElmentos() {
        textView = findViewById(R.id.txt);
        api = RetrofitInstace.getApiService();
    }


}