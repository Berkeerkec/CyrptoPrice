package com.berkesoft.retrofitjava.service;

import com.berkesoft.retrofitjava.model.CyrptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CyrptoAPI {
//Get, Post, Update, Delete

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json


    //URL BASE-> https://raw.githubusercontent.com/

    //GET-> atilsamancioglu/K21-JSONDataSet/master/crypto.json


    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Observable<List<CyrptoModel>> getData();

    //Call<List<CyrptoModel>> getData();



}
