package com.shreerai.digitalcard.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shreerai.digitalcard.Db.Dto.Image;

public class Image_dB extends SQLiteOpenHelper {
    static String Db_contact_image = "Contact_image";
    static int version = 1;

    String create_Table = "CREATE TABLE if not exists 'contacts_card'" +
            "('id'INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "'name' TEXT NOT NULL," +
            "'image' blob NOT NULL)";


    public Image_dB(Context context) {
        super(context, Db_contact_image, null, version);
        getWritableDatabase().execSQL(create_Table);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertInfo(ContentValues contentValues) {
        getWritableDatabase().insert("contacts_ card", "", contentValues);
    }

    public Image getInfo() {
        String sql = "select * from contacts_card";
        Cursor cursor = getWritableDatabase().rawQuery(sql, null);
        Image image = new Image();
        while (cursor.moveToNext()) {
            image.setId(cursor.getInt(cursor.getColumnIndex("id")));
            image.setName(cursor.getString(cursor.getColumnIndex("name")));
            image.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
        }
        cursor.close();
        return image;
    }


}
