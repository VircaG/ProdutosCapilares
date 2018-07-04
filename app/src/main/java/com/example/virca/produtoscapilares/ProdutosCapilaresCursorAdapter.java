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
     private Cursor cursor = null;
     private View.OnClickListener viewHolderClickListener = null;
     private int lastProdutosCapilaresClicked = -1;


     public ProdutosCapilaresCursorAdapter(Context context){
        this.context = context;
    }

    public void refreshData(Cursor cursor){ // Atualizar dados
        if(this.cursor != cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }

    }

    public void setViewHolderClickListener(View.OnClickListener viewHolderClickListener){
         this.viewHolderClickListener = viewHolderClickListener;
    }
    public int getLastProdutosCapilaresClicked(){return lastProdutosCapilaresClicked ;}



    @NonNull
    @Override
    public ProdutosCapilaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View item = LayoutInflater.from(context).inflate(R.layout.item_produtoscapilares,parent,false);

         return new ProdutosCapilaresViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutosCapilaresViewHolder holder, int position) {

        cursor.moveToPosition(position);
        ProdutosCapilares produtosCapilares = BDTableProduto.getCurrentProdutosCapilaresFromCursor(cursor);
        holder.setProdutosCapilares(produtosCapilares);

    }

    @Override
    public int getItemCount() {
        if(cursor== null) return 0;

          return cursor.getCount();
    }


    public class ProdutosCapilaresViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         private TextView textViewNome;
         private TextView textViewQuantidade;
         private int produtosCapilaresId;

        public ProdutosCapilaresViewHolder (View itemView){
            super(itemView);

            textViewNome = (TextView) itemView.findViewById(R.id.TextViewNome);
            textViewQuantidade = (TextView) itemView.findViewById(R.id.textViewQuantidade);

            itemView.setOnClickListener(this);
        }

        public void setProdutosCapilares(ProdutosCapilares produtosCapilares){
             textViewNome.setText(produtosCapilares.getNome());
             textViewQuantidade.setText(produtosCapilares.getQuantidade());
             produtosCapilaresId = produtosCapilares.getId();
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
             if (position == RecyclerView.NO_POSITION){
                 return;
             }
             if (viewHolderClickListener != null){
                 lastProdutosCapilaresClicked = produtosCapilaresId;
                 viewHolderClickListener.onClick(v);
             }
        }
    }
}
