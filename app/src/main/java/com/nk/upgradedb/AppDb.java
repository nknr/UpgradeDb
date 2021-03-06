package com.nk.upgradedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class AppDb extends SQLiteOpenHelper {
    private static final String TAG = AppDb.class.getSimpleName();
    private static final String DB_NAME = "appDb";
    private static final int VERSION = 3;
    private static final String TB_CONTACT = "tb_contact";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";


    public AppDb(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TB_CONTACT + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME + "  VARCHAR2(30), " + LAST_NAME + " VARCHAR2(20)," + EMAIL +" VARCHAR2(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, " upgrade " + oldVersion + " to " + newVersion);

        switch (oldVersion) {

            case 2:
                db.execSQL("ALTER TABLE " + TB_CONTACT + " ADD COLUMN " + EMAIL + " VARCHAR2(20) DEFAULT 'abc@gmail.com';");

        }
    }

    public boolean addUser(String firstName, String lastName,String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, firstName);
        contentValues.put(LAST_NAME, lastName);
        contentValues.put(LAST_NAME, email);


        long result = db.insert(TB_CONTACT, null, contentValues);
        return result != -1;
    }

    public ArrayList<User> getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * from " + TB_CONTACT, null);
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                String firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME));
                String email = cursor.getString(cursor.getColumnIndex(EMAIL));
                users.add(new User(id, firstName, lastName,email));

            }
            cursor.close();
        }
        return users;
    }
}
