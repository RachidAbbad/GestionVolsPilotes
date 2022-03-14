package com.rachid_abbad.gestionvolspilotes.datas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.rachid_abbad.gestionvolspilotes.models.Pilote;
import com.rachid_abbad.gestionvolspilotes.models.Vol;

import java.util.ArrayList;
import java.util.List;

public class ManageDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "ManagePiloteVols";
    public static final int DB_VERSION = 2;

    //Pilotes Infos :
    public static final String TABLE_PILOTE_NAME = "Pilote";
    public static final String PILOTE_MATRICULE = "Matricule";
    public static final String PILOTE_NAME = "Name";

    //Vols Infos :
    public static final String TABLE_VOL_NAME = "Vol";
    public static final String VOL_NUM = "VolNum";
    public static final String VOL_DATE = "DateV";
    public static final String VOL_SOCIETE = "Societe";
    public static final String VOL_MATRICULE = "Matricule";

    public ManageDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String scriptPilote = "CREATE TABLE " + TABLE_PILOTE_NAME + "("
                + PILOTE_MATRICULE + " TEXT PRIMARY KEY,"
                + PILOTE_NAME + " TEXT" + ")";

        String scriptVol = "CREATE TABLE " + TABLE_VOL_NAME + "("
                + VOL_NUM + " TEXT PRIMARY KEY,"
                + VOL_SOCIETE + " TEXT,"
                + VOL_DATE + " TEXT,"
                + VOL_MATRICULE + " TEXT,"
                + "FOREIGN KEY (" + VOL_MATRICULE + ") REFERENCES " + TABLE_PILOTE_NAME + "(" + PILOTE_MATRICULE + "))";

        sqLiteDatabase.execSQL(scriptPilote);
        sqLiteDatabase.execSQL(scriptVol);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PILOTE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VOL_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addPilote(Pilote p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PILOTE_MATRICULE, p.getMatricule());
        values.put(PILOTE_NAME, p.getName());

        db.insert(TABLE_PILOTE_NAME, null, values);
        db.close();
    }

    public void editPilote(Pilote p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PILOTE_MATRICULE, p.getMatricule());
        values.put(PILOTE_NAME, p.getName());

        db.update(TABLE_PILOTE_NAME, values, PILOTE_MATRICULE + "=?", new String[]{String.valueOf(p.getMatricule())});
        db.close();
    }

    public void deletePilote(String matricule) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PILOTE_NAME, PILOTE_MATRICULE + " = ?", new String[]{matricule});
        db.close();
    }

    public List<Pilote> getAllPilotes() {
        List<Pilote> list = new ArrayList<Pilote>();

        String selectQuery = "SELECT  * FROM " + TABLE_PILOTE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pilote p = new Pilote();
                p.setMatricule(cursor.getString(0));
                p.setName(cursor.getString(1));

                list.add(p);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<Pilote> searchPilote(String key) {
        List<Pilote> list = new ArrayList<Pilote>();

        String selectQuery = "SELECT  * FROM " + TABLE_PILOTE_NAME + " WHERE +" + PILOTE_NAME + " LIKE ? OR " + PILOTE_MATRICULE + " LIKE ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"%" + key + "%", "%" + key + "%"});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pilote pi = new Pilote();
                pi.setMatricule(cursor.getString(0));
                pi.setName(cursor.getString(1));

                list.add(pi);
            } while (cursor.moveToNext());
        }

        return list;
    }




    public void addVol(Vol v) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VOL_NUM, v.getNumVol());
        values.put(VOL_SOCIETE, v.getSociete());
        values.put(VOL_DATE, v.getDate());
        values.put(VOL_MATRICULE, v.getNumMatricule());

        db.insert(TABLE_VOL_NAME, null, values);
        db.close();
    }

    public void editVol(Vol v) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VOL_NUM, v.getNumVol());
        values.put(VOL_SOCIETE, v.getSociete());
        values.put(VOL_DATE, v.getDate());
        values.put(VOL_MATRICULE, v.getNumMatricule());

        db.update(TABLE_VOL_NAME, values, VOL_NUM + "=?", new String[]{String.valueOf(v.getNumVol())});
        db.close();
    }

    public void deleteVol(String num_vol) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VOL_NAME, VOL_NUM + " = ?", new String[]{num_vol});
        db.close();
    }

    public List<Vol> getAllVols() {
        List<Vol> list = new ArrayList<Vol>();

        String selectQuery = "SELECT  * FROM " + TABLE_VOL_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vol v = new Vol();

                v.setNumVol(cursor.getString(0));
                v.setSociete(cursor.getString(1));
                v.setDate(cursor.getString(2));
                v.setNumMatricule(cursor.getString(3));

                list.add(v);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<Vol> searchVol(String key) {
        List<Vol> list = new ArrayList<Vol>();

        String selectQuery = "SELECT  * FROM " + TABLE_VOL_NAME + " WHERE +" + VOL_NUM
                + " LIKE ? OR " + VOL_SOCIETE + " LIKE ?"
                + " OR " + VOL_DATE + " LIKE ?"
                + " OR " + VOL_MATRICULE + " LIKE ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"%" + key + "%", "%" + key + "%","%" + key + "%","%" + key + "%"});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vol v = new Vol();

                v.setNumVol(cursor.getString(0));
                v.setSociete(cursor.getString(1));
                v.setDate(cursor.getString(2));
                v.setNumMatricule(cursor.getString(3));

                list.add(v);
            } while (cursor.moveToNext());
        }

        return list;
    }


}
