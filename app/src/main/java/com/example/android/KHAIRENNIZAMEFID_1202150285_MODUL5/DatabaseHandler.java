package com.example.android.KHAIRENNIZAMEFID_1202150285_MODUL5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "db_praktikum";

    private static final String TABLE_NAME = "tb_todo";

    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "desc";
    private static final String COL_4 = "priority";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //create DB
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL_1+ " INTEGER PRIMARY KEY," + COL_2 + " TEXT,"
                + COL_3 + " TEXT, " +COL_4 + " TEXT)";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public void save(ToDo todo){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COL_2, todo.getName());
        values.put(COL_3, todo.getDesc());
        values.put(COL_4, todo.getPriority());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public LinkedList<ToDo> findAll(){
        LinkedList<ToDo> listBuku = new LinkedList<ToDo>();
        String query="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                ToDo todo = new ToDo();
                todo.setId(Integer.valueOf(cursor.getString(0)));
                todo.setName(cursor.getString(1));
                todo.setDesc(cursor.getString(2));
                todo.setPriority(cursor.getString(3));
                listBuku.add(todo);
            }while(cursor.moveToNext());
        }

        return listBuku;
    }

    //ketika kita delete dari database nya
    public boolean delete(ToDo todo){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id =" + todo.getId(), null) > 0;
    }
}
