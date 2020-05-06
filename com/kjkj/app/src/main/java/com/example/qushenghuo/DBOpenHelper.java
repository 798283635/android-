package com.example.qushenghuo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBOpenHelper extends SQLiteOpenHelper {
     //声明一个AndroidSDK自带的数据库变量db
    private SQLiteDatabase db;

    public DBOpenHelper(Context context){
        super(context,"db_test",null,1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT," +
                "sex TEXT," +
                "school TEXT,"+
                "email TEXT," +
                "phone TEXT,"+
                "card TEXT," +
                "id TEXT)"
                );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void add(String name, String password){
        //db.execSQL("INSERT INTO user (name,password,id,sex,email,card,phone,school) VALUES(?,?,?,?,?,?,?,?)",new Object[]{name,password,id,sex,email,card,phone,school});
    }
    public void delete(String name, String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    /*public static ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = User.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String card = cursor.getString(cursor.getColumnIndex("card"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String school = cursor.getString(cursor.getColumnIndex("school"));
            list.add(new User(name,password,id,sex,email,card,phone,school));
        }
        return list;
    }*/
}
