package hallal.personalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CredentialsSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="UserCredentials";
    public static final int DB_VERSION=1;

    public CredentialsSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CREDENTIAL (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "EMAIL TEXT," +
                "PHONE_NUMBER TEXT," +
                "PASSWORD TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertCredentials(SQLiteDatabase db, String name, String email, String phone, String password)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", name);
        contentValues.put("EMAIL", email);
        contentValues.put("PHONE_NUMBER", phone);
        contentValues.put("PASSWORD", password);

        db.insert("CREDENTIAL", null, contentValues);
    }
}
