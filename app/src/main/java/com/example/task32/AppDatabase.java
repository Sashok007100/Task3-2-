package com.example.task32;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.myapplication.model.Student;
import com.example.myapplication.modelinterface.StudentDao;
import com.example.myapplication.modelinterface.Transactor;

@Database(entities = {Student.class}, version = 2)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao getStudentDao();
    public abstract Transactor getTeansactor();
    private static AppDatabase db;
    public static final Migration MIGRATION_1_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE IF NOT EXISTS Students2" + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + "Sername TEXT," + "Name TEXT," + "Secondname TEXT," + "date INTEGER )"
            );

            Cursor c = database.query("SELECT id, FIO, date FROM Student", null);
            ContentValues cv = new ContentValues();
            if (c.moveToFirst()){
                do {
                    cv.clear();
                    cv.put("id", c.getInt(0));
                    String FIO = c.getString(1);
                    cv.put("Sername", FIO.substring(0, FIO.indexOf(' ')));
                    cv.put("Name", FIO.substring(FIO.indexOf(' ')+1, FIO.substring(FIO.indexOf(' ')+1).indexOf(' ') + FIO.indexOf(' ')+1));
                    cv.put("Secondname", FIO.substring(FIO.substring(FIO.indexOf(' ')+1).indexOf(' ') + FIO.indexOf(' ')+2));
                    cv.put("Date", c.getLong(2));
                    database.insert("Students2", SQLiteDatabase.CONFLICT_ROLLBACK, cv);
                }while (c.moveToNext());
            }

            database.execSQL("DROP TABLE IF EXISTS Student");
            database.execSQL("ALTER TABLE Students2 RENAME TO Student");
            c.close();
        }
    };

    public static AppDatabase getDatabace(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "databace").addMigrations(MIGRATION_1_2).build();
            }
        return db;
    }
}