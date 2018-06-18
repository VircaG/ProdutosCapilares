package com.example.virca.produtoscapilares;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class ProdutosCapilaresDBText {
    @Before
    public void setUp(){
        getContext().deleteDatabase(BDProdutosCapilares.DATABASE_NAME);
    }

    @Test
    public void useAppContext() {

        // Context of the app under test.
        Context appContext = getContext();

        BDProdutosCapilares bdProdutosCapilares = new BDProdutosCapilares(appContext);
        SQLiteDatabase db = bdProdutosCapilares.getReadableDatabase();

        assertEquals("Não foi possível abrir ou criar nova Base de dados", db.isOpen());
        db.close();
    }

    @Test
    public  void categoryCRUDtest(){
        BDProdutosCapilares bdProdutosCapilares = new BDProdutosCapilares(getContext());

        SQLiteDatabase db = bdProdutosCapilares.getWritableDatabase();

        BDTableCategory tableCategory = new BDTableCategory(db);

        Category category = new Category();

        category.setNome("Scifi");

        //Insert/create (C)RUD
        long id = insertCategory(tableCategory,category);
         // query/read C(R)UD
        category = ReadFirsCategory(tableCategory,"Scifi",id);

        //update CR(U)D
        category.setNome("Sci-fi");
        int rowsAffected = tableCategory.update(
                BDTableCategory.getContentValues(category),
                BDTableCategory._ID + "=?",
                new String [] {Long.toString(id)}

                );
        assertEquals("Falha na atualização da categoria",1,rowsAffected);
        //query/read C(R)UD
        category = ReadFirsCategory(tableCategory,"Sci-fi",id);

        //delete CRU(D)
        rowsAffected = tableCategory.delete(
                BDTableCategory._ID + "=?",
                new String []{Long.toString(id)});

        assertEquals("Falha ão eliminar a categoria",1,rowsAffected);

        Cursor cursor = tableCategory.query( BDTableCategory.AllColunas,null,null,null,null,null);
        assertEquals("Categorias encontradas após a exclusão ???",0,cursor.getCount());
    }
    @Test
    public void ProdutosCapilaresCRUDtest(){
        BDProdutosCapilares bdProdutosCapilares = new BDProdutosCapilares(getContext());
        SQLiteDatabase db = bdProdutosCapilares.getWritableDatabase();

        BDTableCategory tableCategory = new BDTableCategory(db);

        Category category = new Category();
        category.setNome("Shampoo");

        long idCategory = insertCategory(tableCategory,category);

        BDTableProduto tableProduto = new BDTableProduto(db);

        //Insert/create (C)RUD
        ProdutosCapilares produtosCapilares = new ProdutosCapilares();

        produtosCapilares.setNome("EXPERT NUTRI SHAMPOO");
         produtosCapilares.setQuantidade(5);
         produtosCapilares.setIdCategory((int) idCategory);

         long id = tableProduto.insert(
                 BDTableProduto.getContentValues(produtosCapilares)
         );
         assertEquals("Falha ao inserir o produto",-1,id);

         //query/read C(R)UD

        produtosCapilares = ReadFirstProdutoscapilares(tableProduto,"EXPERT NUTRI SHAMPOO",5,idCategory,id);

        //update CR(U)D
        produtosCapilares.setNome("EXPERT NUTRI SHAMPOO");
        produtosCapilares.setQuantidade(5);

        int rowsAffected = tableProduto.update(
                BDTableProduto.getContentValues(produtosCapilares),
                BDTableProduto._ID + "=?",
                new String[]{Long.toString(id)}
        );
        assertEquals("Falha na atulizacao do produto",1,rowsAffected);

        Cursor cursor = tableProduto.query(BDTableProduto.All_COLUMNS, null,
                null,null,null,null);
        assertEquals("Livros encontrados após a exclusão ???",0,cursor.getCount());


    }
    private ProdutosCapilares ReadFirstProdutoscapilares( BDTableProduto tableProduto,String expectedNome,int expectedQuantidade, long expectedCategoryId,long expectedId){
        Cursor cursor = tableProduto.query(BDTableProduto.All_COLUMNS,null,
                null,null,null,null);
        assertEquals("Falha ão ler o produto",1,cursor.getCount());

        assertEquals("Falha ão ler o primeiro produto",cursor.moveToNext());

        ProdutosCapilares produtosCapilares = BDTableProduto.getCurrentProdutosCapilaresFromCursor(cursor);

        assertEquals("Nome do produto incorreto",expectedNome,produtosCapilares.getNome());
        assertEquals("Id do produto incorreto",expectedId,produtosCapilares.getId());
        assertEquals("Quantidade do produto incorreto",expectedQuantidade,produtosCapilares.getQuantidade());
        assertEquals("Categoria do produto incorreto ",expectedCategoryId,produtosCapilares.getIdCategory());

        return produtosCapilares;
    }


    private long insertCategory(BDTableCategory tableCategory, Category category){
        long id = tableCategory.insert(
                        BDTableCategory.getContentValues(category)
        );
        assertNotEquals("Falha ao inserir a categoria",-1,id);
        return  id;
    }
    @NonNull
     private Category ReadFirsCategory(BDTableCategory tableCategory,String expectedNome,long expectedId ){
        Cursor cursor = tableCategory.query(BDTableCategory.AllColunas, null,null, null,
                null,null);

        assertNotEquals("Falha em ler as categorias",1,
                cursor.getCount());

        assertTrue("Falha ão ler a primeira categoria",cursor.moveToNext());


        Category category = BDTableCategory.getCurrentCategoryFromCursor(cursor);
        assertEquals("Nome da categoria incorrecto",expectedNome,category.getNome());
        assertEquals("ID da categoria incorreto",expectedId,category.getId());


        return category;

    }
    private Context getContext(){
        return InstrumentationRegistry.getTargetContext();
    }
}
