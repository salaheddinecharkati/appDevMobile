package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Inscription extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inscription.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "utilisateurs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_VILLE = "ville";
    public static final String COLUMN_MOT_DE_PASSE = "mot_de_passe";
    public static final String COLUMN_USER_TYPE= "userType";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_VILLE + " TEXT,"
            + COLUMN_MOT_DE_PASSE + " TEXT,"
            + COLUMN_USER_TYPE + " TEXT"
            // Ajoutez d'autres colonnes selon vos besoins
            + ")";

    public Inscription(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public long insererUtilisateur(String email, String ville,String mot_de_passe,String userType ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_VILLE, ville);
        values.put(COLUMN_MOT_DE_PASSE, mot_de_passe);
        // Ajoutez d'autres valeurs pour d'autres colonnes
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

}

