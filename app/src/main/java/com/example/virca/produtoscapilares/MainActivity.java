package com.example.virca.produtoscapilares;

import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUTOSCAPILARES_CURSOR_LOADER_ID = 0;
   // public static  final String PRODUTOSCAPILARES_ID = "PRODUTOSCAPILARES_ID";


    private ProdutosCapilaresCursorAdapter produtosCapilaresCursorAdapter;
    private RecyclerView recyclerViewProdutosCapilares ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewProdutosCapilares = (RecyclerView) findViewById(R.id.recyclerViewProdutosCapilares);


        recyclerViewProdutosCapilares.setLayoutManager(new LinearLayoutManager(this));
        produtosCapilaresCursorAdapter = new ProdutosCapilaresCursorAdapter(this);
        recyclerViewProdutosCapilares.setAdapter(produtosCapilaresCursorAdapter);

        getLoaderManager().initLoader(PRODUTOSCAPILARES_CURSOR_LOADER_ID,null,(android.app.LoaderManager.LoaderCallbacks<Cursor>)this);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
    }
    public Loader <Cursor> onCreateLoader(int id, @Nullable Bundle args){
        if (id == PRODUTOSCAPILARES_CURSOR_LOADER_ID) {
            return  new CursorLoader(this,ProdutosCapilaresContentProvider.BASE_URI,BDTableProduto.All_COLUMNS,null,null,null);
        }
        return null;

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        produtosCapilaresCursorAdapter.refreshData(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        produtosCapilaresCursorAdapter.refreshData(null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
