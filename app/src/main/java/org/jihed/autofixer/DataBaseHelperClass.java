package org.jihed.autofixer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "carmanagement.db";
    //Database Table name
    private static final String TABLE_NAME = "User";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL);";
    //Constructor
    public DataBaseHelperClass(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    public DataBaseHelperClass(Context context, int factory, SQLiteDatabase.OpenParams version, String name) {
        super(context, name, factory, version);
    }

    public DataBaseHelperClass(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DataBaseHelperClass(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Employee Data
    public void addUser(UserModelClass userModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelperClass.NAME, userModelClass.getName());
        contentValues.put(DataBaseHelperClass.EMAIL, userModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DataBaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<UserModelClass> getUserList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<UserModelClass> storeUser = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                storeUser.add(new UserModelClass(id,name,email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeUser;
    }

    public void updateUser(UserModelClass userModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelperClass.NAME,userModelClass.getName());
        contentValues.put(DataBaseHelperClass.EMAIL,userModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(UserModelClass.getId())});
    }

    public void deleteUser(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}
