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

    public static final String TABLE_NOTIFICATIONS = "notifications";
    public static final String TABLE_NOTIFICATIONS_ID = "id";
    public static final String TABLE_NOTIFICATIONS_USER = "user";
    public static final String TABLE_NOTIFICATIONS_IMAGE = "image";
    public static final String TABLE_NOTIFICATIONS_CONTENT = "content";
    public static final String TABLE_NOTIFICATIONS_DATE = "date";

    public static final String CREATE_TABLE_NOTIFICATIONS = "CREATE TABLE " + TABLE_NOTIFICATIONS + " (" +
            TABLE_NOTIFICATIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TABLE_NOTIFICATIONS_USER + " TEXT, " +
            TABLE_NOTIFICATIONS_IMAGE + " TEXT, " +
            TABLE_NOTIFICATIONS_CONTENT + " TEXT, " +
            TABLE_NOTIFICATIONS_DATE + " TEXT)";

    public static final String SELECT_NOTIFICATIONS = "SELECT * FROM " + TABLE_NOTIFICATIONS;
}
