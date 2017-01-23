package omadara.doctorappase3;

/**
* Created by plzdo on 22/1/2017.
 */

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper{

    public MyDatabase(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    public void deleteAv(ArrayList<String> names) {
        SQLiteDatabase db = getWritableDatabase();
        for(int i = 0; i < names.size(); i++) {
            String[] sels = new String[]{names.get(i)};
            db.delete("Avs", "avs = ?", sels);
        }
        db.close();
    }
    public boolean updateAvXeirourgeio(String name, String hour, String date, boolean reserved, String difficulty) {
        SQLiteDatabase db1 = getReadableDatabase();
        String[] sel = new String[]{name};
        Cursor c = db1.rawQuery("SELECT avs FROM Avs WHERE avs = ?", sel);
        if(c.getCount() != 0) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("Avs", "avs= ?", sel);
            ContentValues values = new ContentValues();
            values.put("avs", name);
            values.put("date", date);
            values.put("status", reserved);
            values.put("hour", hour);
            values.put("difficulty", difficulty);
            db.insert("Avs", null, values);
            db1.close();
            db.close();
            return true;
        } else {
            db1.close();
            return false;
        }
    }

    public boolean updateAvEpiskepsi(String name, String hour, String date, boolean reserved) {
        SQLiteDatabase db1 = getReadableDatabase();
        String[] sel = new String[]{name};
        Cursor c = db1.rawQuery("SELECT avs FROM Avs WHERE avs = ?", sel);
        if(c.getCount() != 0) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("Avs", "avs= ?", sel);
            ContentValues values = new ContentValues();
            values.put("avs", name);
            values.put("date", date);
            values.put("status", reserved);
            values.put("hour", hour);
            db.insert("Avs", null, values);
            db1.close();
            db.close();
            return true;
        } else {
            db1.close();
            return false;
        }
    }

    public boolean addAvXeirourgeio(String name, String hour, String date, boolean reserved, String difficulty) {
        SQLiteDatabase db1 = getReadableDatabase();
        String[] sel = new String[]{name};
        Cursor c = db1.rawQuery("SELECT avs FROM Avs WHERE avs = ?", sel);
        if(c.getCount() == 0) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("avs", name);
            values.put("date", date);
            values.put("status", reserved);
            values.put("hour", hour);
            values.put("difficulty", difficulty);
            db.insert("Avs", null, values);
            db1.close();
            db.close();
            return true;
        } else {
            db1.close();
            return false;
        }
    }

    public boolean addAvEpiskepsi(String name, String hour, String date, boolean reserved) {
        SQLiteDatabase db1 = getReadableDatabase();
        String[] sel = new String[]{name};
        Cursor c = db1.rawQuery("SELECT avs FROM Avs WHERE avs = ?", sel);
        if(c.getCount() == 0) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("avs", name);
            values.put("date", date);
            values.put("status", reserved);
            values.put("hour", hour);
            db.insert("Avs", null, values);
            db1.close();
            db.close();
            return true;
        } else {
            db1.close();
            return false;
        }
    }

    public Cursor getEventsAdmin() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Avs", null);
        c.moveToFirst();
        db.close();
        return c;
    }
}
