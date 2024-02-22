// Declaraciones de paquetes
package com.saaweel.instadam.database;

import java.util.Map;

/**
 * Clase DBFrame
 * Esta clase proporciona una estructura de base de datos para la aplicación.
 */
public class DBFrame {
    public static final String DATABASE_NAME = "instadam.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users";
    public static final String TABLE_USERS_ID = "id";
    public static final String TABLE_USERS_USERNAME = "username";
    public static final String TABLE_USERS_EMAIL = "email";
    public static final String TABLE_USERS_PASSWORD = "password";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
            TABLE_USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TABLE_USERS_USERNAME + " TEXT, " +
            TABLE_USERS_EMAIL + " TEXT, " +
            TABLE_USERS_PASSWORD + " TEXT)";
}
