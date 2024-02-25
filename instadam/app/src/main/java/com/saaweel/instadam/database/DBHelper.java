// Declaraciones de paquetes
package com.saaweel.instadam.database;

// Declaraciones de librerías
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saaweel.instadam.models.Noti;

import java.util.ArrayList;

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
        db.execSQL(DBFrame.CREATE_TABLE_NOTIFICATIONS);

        // Simulación de notificaciones
        db.execSQL("INSERT INTO " + DBFrame.TABLE_NOTIFICATIONS + " (" +
                DBFrame.TABLE_NOTIFICATIONS_USER + ", " +
                DBFrame.TABLE_NOTIFICATIONS_IMAGE + ", " +
                DBFrame.TABLE_NOTIFICATIONS_CONTENT + ", " +
                DBFrame.TABLE_NOTIFICATIONS_DATE + ") VALUES ('saaweel', '', 'Notificación de prueba', '2024-01-01 00:00:00')");

        db.execSQL("INSERT INTO " + DBFrame.TABLE_NOTIFICATIONS + " (" +
                DBFrame.TABLE_NOTIFICATIONS_USER + ", " +
                DBFrame.TABLE_NOTIFICATIONS_IMAGE + ", " +
                DBFrame.TABLE_NOTIFICATIONS_CONTENT + ", " +
                DBFrame.TABLE_NOTIFICATIONS_DATE + ") VALUES ('jesustucci_', 'https://i.imgur.com/umrnAix.jpg', 'Le ha gustado tu publicación', '2024-02-01 10:33:33')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBFrame.TABLE_NOTIFICATIONS);
        onCreate(db);
    }

    @SuppressLint("Range")
    public String[][] getNotifications() {
        String[][] notifications = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(DBFrame.SELECT_NOTIFICATIONS, null);

        if (cursor.moveToFirst()) {
            notifications = new String[cursor.getCount()][4];
            int i = 0;
            do {
                notifications[i][0] = cursor.getString(cursor.getColumnIndex(DBFrame.TABLE_NOTIFICATIONS_USER));
                notifications[i][1] = cursor.getString(cursor.getColumnIndex(DBFrame.TABLE_NOTIFICATIONS_IMAGE));
                notifications[i][2] = cursor.getString(cursor.getColumnIndex(DBFrame.TABLE_NOTIFICATIONS_CONTENT));
                notifications[i][3] = cursor.getString(cursor.getColumnIndex(DBFrame.TABLE_NOTIFICATIONS_DATE));
                i++;
            } while (cursor.moveToNext());
        }

        cursor.close();

        return notifications;
    }
}
