// Declaraciones de paquetes
package com.saaweel.instadam.database;

// Declaraciones de librerías
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase DBHelper
 * Esta clase actúa como controlador de la base de datos SQLite para la aplicación.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, DBFrame.DATABASE_NAME, null, DBFrame.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBFrame.CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBFrame.TABLE_USERS);
        onCreate(db);
    }
}
