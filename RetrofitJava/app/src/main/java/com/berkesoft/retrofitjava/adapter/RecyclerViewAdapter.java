package com.berkesoft.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.berkesoft.retrofitjava.databinding.RecyclerRowBinding;
import com.berkesoft.retrofitjava.model.CyrptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.AdapterHolder> {

    private ArrayList<CyrptoModel> cyrptoModels;
    public RecyclerViewAdapter(ArrayList<CyrptoModel> cyrptoModels){
        this.cyrptoModels = cyrptoModels;
    }
    private String[] color = {"#00ff00","#ff8000","#00bfff","#ff00ff","#bf00ff","#ffff00","#00ffbf","#ffbf00",};


    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent ,false);
        return new AdapterHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(color[position % 8]));
        holder.recyclerRowBinding.TextViewName.setText(cyrptoModels.get(position).currency);
        holder.recyclerRowBinding.TextViewPrice.setText(cyrptoModels.get(position).price);

    }

    @Override
    public int getItemCount() {
        return cyrptoModels.size();
    }




    public class AdapterHolder extends RecyclerView.ViewHolder {

        private RecyclerRowBinding recyclerRowBinding;
        public AdapterHolder(RecyclerRowBinding recyclerRowBinding) {
        super(recyclerRowBinding.getRoot());
        this.recyclerRowBinding = recyclerRowBinding;
    }
}




}
