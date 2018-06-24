package com.example.virca.produtoscapilares;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProdutosCapilaresCursorAdapter extends RecyclerView.Adapter<ProdutosCapilaresCursorAdapter.ProdutosCapilaresViewHolder> {
    private TextView textViewNome;
    private TextView textViewQuantidade;
    private int produtoId;


    //public ProdutosCapilaresViewHolder(View itemView){
        //super(itemView);

       // textViewNome TextView = itemView.findViewById(R.id.textViewNome);
        //textViewQuantidade =(TextView)itemView.findViewById(R.id.textViewQuantidade);

    //}

    @NonNull
    @Override
    public ProdutosCapilaresCursorAdapter.ProdutosCapilaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutosCapilaresCursorAdapter.ProdutosCapilaresViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ProdutosCapilaresViewHolder extends RecyclerView.ViewHolder{
        public ProdutosCapilaresViewHolder (View itemView){
            super(itemView);
        }
    }
}
