package com.example.virca.produtoscapilares;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProdutosCapilaresCursorAdapter extends RecyclerView.Adapter<ProdutosCapilaresCursorAdapter.ProdutosCapilaresViewHolder> {
     private Context context;

    public ProdutosCapilaresCursorAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public ProdutosCapilaresCursorAdapter.ProdutosCapilaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View item = LayoutInflater.from(context).inflate(R.layout.item_produtoscapilares,parent,false);

         return new ProdutosCapilaresViewHolder(item);
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
