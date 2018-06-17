package com.example.virca.produtoscapilares;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BDTableCategory implements BaseColumns {
    private static final String FIELD_NOME = "nome";

    public static final String TABLE_NAME = "categorias";


    public static final String  [] AllColunas = new String[] {_ID, FIELD_NOME};
    private SQLiteDatabase db;

    public BDTableCategory(SQLiteDatabase db){
        this.db = db;
    }
    public void create(){
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" + FIELD_NOME +
                " TEXT NOT NULL," + _ID +
                "INTEGER PRIMARY KEY AUTOINCREMENT)"
        );
    }

    public static ContentValues getContentValues(Category category){
        ContentValues values = new ContentValues();

        values.put(_ID,category.getId());
        values.put(FIELD_NOME,category.getNome());

        return values;
    }
    public static Category getCurrentCategoryFromCursor(Cursor cursor){
     final int posId = cursor.getColumnIndex(_ID) ;
     final int postNome = cursor.getColumnIndex(FIELD_NOME);

     Category category = new Category();

     category.setId(cursor.getInt(posId));
     category.setNome(cursor.getString(postNome));
     return  category;

    }

    public long insert (ContentValues values){
        return db.insert(TABLE_NAME,"null",values); }





        //public int delete(String whereClause,String[] whereArgs){
        //return db.insert(TABLE_NAME,whereClause,whereArgs);
    //}
    public Cursor query(String [] columns ,String selection,
                        String[] selectionArgs,String groupBy,String having,
                        String orderBy){
        return db.query(TABLE_NAME,columns,selection,selectionArgs,
                groupBy,having,orderBy);

    }
}
