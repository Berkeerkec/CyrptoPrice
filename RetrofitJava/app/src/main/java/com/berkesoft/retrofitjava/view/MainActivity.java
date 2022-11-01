package com.berkesoft.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.berkesoft.retrofitjava.R;
import com.berkesoft.retrofitjava.adapter.RecyclerViewAdapter;
import com.berkesoft.retrofitjava.databinding.ActivityMainBinding;
import com.berkesoft.retrofitjava.model.CyrptoModel;
import com.berkesoft.retrofitjava.service.CyrptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private ActivityMainBinding binding;
    ArrayList<CyrptoModel> cyrptoModels;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerViewAdapter recyclerViewAdapter;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        loadData();


    }

    private void loadData(){

        final CyrptoAPI cyrptoAPI = retrofit.create(CyrptoAPI.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cyrptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));







        /*

        Call<List<CyrptoModel>> call = cyrptoAPI.getData();

        call.enqueue(new Callback<List<CyrptoModel>>() {
            @Override
            public void onResponse(Call<List<CyrptoModel>> call, Response<List<CyrptoModel>> response) {
                if (response.isSuccessful()){
                    List<CyrptoModel> responseList = response.body();
                    cyrptoModels = new ArrayList<>(responseList);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(cyrptoModels);
                    binding.recyclerView.setAdapter(recyclerViewAdapter);

                }

                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CyrptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

*/

    }

    private void handleResponse(List<CyrptoModel> cyrptoList){
        cyrptoModels = new ArrayList<>(cyrptoList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(cyrptoModels);
        binding.recyclerView.setAdapter(recyclerViewAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}