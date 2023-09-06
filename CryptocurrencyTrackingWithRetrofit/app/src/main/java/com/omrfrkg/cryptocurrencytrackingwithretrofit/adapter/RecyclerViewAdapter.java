package com.omrfrkg.cryptocurrencytrackingwithretrofit.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omrfrkg.cryptocurrencytrackingwithretrofit.R;
import com.omrfrkg.cryptocurrencytrackingwithretrofit.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<CryptoModel> cryptoModels;
    private String[] colors = {"#cdf7f9","#ff4000","#ffe500","#c8c9b4","#ffa500","#fffdcc","#38b6ff","#b3bed4","#ffbf97"};

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoModels) {
        this.cryptoModels = cryptoModels;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.bind(cryptoModels.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoModels.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView textName;
        TextView textPrice;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CryptoModel cryptoModel,String[] colors,int position){
            itemView.setBackgroundColor(Color.parseColor(colors[position%colors.length]));
            textName = itemView.findViewById(R.id.nameText);
            textPrice = itemView.findViewById(R.id.priceText);

            textPrice.setText(cryptoModel.price);
            textName.setText(cryptoModel.name);
        }
    }
}
