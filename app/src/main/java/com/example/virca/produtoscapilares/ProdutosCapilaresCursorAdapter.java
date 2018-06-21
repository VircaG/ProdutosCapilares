package com.example.virca.produtoscapilares;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ProdutosCapilaresCursorAdapter extends RecyclerView.Adapter<ProdutosCapilaresCursorAdapter.ProdutosCapilaresViewHolder> {

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
