package com.omrfrkg.cryptocurrencytrackingwithretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omrfrkg.cryptocurrencytrackingwithretrofit.R;
import com.omrfrkg.cryptocurrencytrackingwithretrofit.adapter.RecyclerViewAdapter;
import com.omrfrkg.cryptocurrencytrackingwithretrofit.model.CryptoModel;
import com.omrfrkg.cryptocurrencytrackingwithretrofit.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://rest.coinapi.io/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        //Retrofit & JSON

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();
    }

    private void loadData(){
        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(MainActivity.this :: handleResponse));


        /*
        Call<List<CryptoModel>> data = cryptoAPI.getData();

        data.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()){
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    //RecyclerView

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void handleResponse(List<CryptoModel> cryptoModelsList){

        cryptoModels = new ArrayList<>(cryptoModelsList);

        //RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
