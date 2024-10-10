package hallal.personalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RequestsSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="Requests";
    public static final int DB_VERSION=1;


    public RequestsSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE REQUEST (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "BLOODTYPE TEXT," +
                "LOCATION TEXT," +
                "PHONE_NUMBER TEXT," +
                "ALTERNATIVE_NAME TEXT," +
                "OUTPUT TEXT," +
                "ALTERNATIVE_PHONE TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertRequest(SQLiteDatabase db, String name, String bloodType, String location, String phone, String alternativeName, String alternativePhone, String output)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", name);
        contentValues.put("BLOODTYPE", bloodType);
        contentValues.put("LOCATION", location);
        contentValues.put("ALTERNATIVE_NAME", alternativeName);
        contentValues.put("ALTERNATIVE_PHONE", phone);
        contentValues.put("OUTPUT", output);

        db.insert("REQUEST", null, contentValues);
    }
}
