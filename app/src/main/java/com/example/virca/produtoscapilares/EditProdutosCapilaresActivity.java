package com.example.virca.produtoscapilares;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class EditProdutosCapilaresActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CATEGORIES_CURSOR_LOADER_ID = 0;

    private EditText editTextNome;
    private  EditText editTextQuantidade;
    private Spinner spinnerCategory;
    private ProdutosCapilares produtosCapilares;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produtoscapilares);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        int produtosCapilaresId = intent.getIntExtra(MainActivity.PRODUTOSCAPILARES_ID,-1);

        if (produtosCapilaresId == -1){
            finish();
            return;
        }

        Cursor cursorProdutoscapilares = getContentResolver().query(
                Uri.withAppendedPath(ProdutosCapilaresContentProvider.BASE_URI,Integer.toString(produtosCapilaresId)),
                BDTableProduto.All_COLUMNS,
                null,
                null,
                null
        );
        if(!cursorProdutoscapilares.moveToNext()){
            Toast.makeText(this,"Produtos Capilares não encontrada",Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextQuantidade =  (EditText)findViewById(R.id.editTextQuantidade);
        spinnerCategory = findViewById(R.id.spinnerCategory);


        produtosCapilares = BDTableProduto.getCurrentProdutosCapilaresFromCursor(cursorProdutoscapilares);

        editTextNome.setText(produtosCapilares.getNome());
        editTextQuantidade.setText(produtosCapilares.getQuantidade());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportLoaderManager().initLoader(CATEGORIES_CURSOR_LOADER_ID, null, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(CATEGORIES_CURSOR_LOADER_ID, null,this);
    }

    public void cancel(View view){finish();}

    public void save (View view){
        //falta codigo

        produtosCapilares.setNome(editTextNome.getText().toString());
        produtosCapilares.setQuantidade(Integer.parseInt(editTextQuantidade.getText().toString()));
        produtosCapilares.setIdCategory((int) spinnerCategory.getSelectedItemId());
       // produtosCapilares.setDate();

        int recordsAffected = getContentResolver().update(
                Uri.withAppendedPath(ProdutosCapilaresContentProvider.PRODUTOSCAPILARES_URI,Integer.toString(produtosCapilares.getId())),
                BDTableProduto.getContentValues(produtosCapilares),
                null,
                null
        );
        if (recordsAffected > 0){
            Toast.makeText(this,"Produtos Capilares salvo com sucesso",Toast.LENGTH_LONG).show();
         finish();
         return;
        }
        Toast.makeText(this,"Não foi possível salvar o Produto Capilar",Toast.LENGTH_LONG).show();
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id,@Nullable Bundle args) {
        if(id == CATEGORIES_CURSOR_LOADER_ID){
            return new CursorLoader(this,ProdutosCapilaresContentProvider.CATEGORIES_URI,BDTableCategory.AllColunas,null,null,null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        SimpleCursorAdapter cursorAdapterCategories = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                data,
                new String[]{BDTableCategory.TABLE_NAME},
                new int[]{android.R.id.text1}
                );

        spinnerCategory.setAdapter(cursorAdapterCategories);

        int idCategory = produtosCapilares.getIdCategory();
         for(int i = 0; i< spinnerCategory.getCount(); i ++){
             Cursor cursor = (Cursor) spinnerCategory.getItemAtPosition(i);

             final int posId = cursor.getColumnIndex(BDTableCategory._ID);

             if(idCategory == cursor.getInt(posId)){
                 spinnerCategory.setSelection(i);
                 break;
             }
         }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
