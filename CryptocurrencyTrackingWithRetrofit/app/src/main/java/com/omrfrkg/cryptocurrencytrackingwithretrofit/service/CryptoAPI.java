package com.omrfrkg.cryptocurrencytrackingwithretrofit.service;

import com.omrfrkg.cryptocurrencytrackingwithretrofit.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    //GET, POST, UPDATE, DELETE ;

    @GET("APIKEY-D26AD6F9-B7E5-486F-A99C-67ECE14CECFA/v1/exchanges")
    Observable<List<CryptoModel>> getData();
    //Call<List<CryptoModel>> getData();


}
