package pe.edu.continental.adaug.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int BD_version = 1;
    public static final String TABLE_NOMBRE ="DBLiteGps";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_DIRECCION="direccion";

    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NOMBRE, null, BD_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//insertar datos
        String sql= "CREATE TABLE "+ TABLE_NOMBRE
                + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COLUMN_DIRECCION + " VARCHAR);  ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//orden para las nuevas bd
        String sql = "DROP TABLE IF EXISTS DBLiteGps";
        db.execSQL(sql);
        onCreate(db);

    }
    public boolean addData(String insertar){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DIRECCION, insertar);
        long result = db.insert(TABLE_NOMBRE, null, contentValues);
        //valida
        if (result== -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getListaUbic(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NOMBRE, null);
        return data;
    }
}
