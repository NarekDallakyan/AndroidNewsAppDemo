package am.ith.app.db_engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import static am.ith.app.db_engine.Constants.DB_NAME;
import static am.ith.app.db_engine.Constants.DB_TABLE;
import static am.ith.app.db_engine.Constants.DB_VERSION;
import static am.ith.app.db_engine.Constants.ID;
import static am.ith.app.db_engine.Constants.IS_SELECTED;
import static am.ith.app.db_engine.Constants.POSITION;

public class Services extends SQLiteOpenHelper {

    public Services(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                POSITION + " TEXT NOT NULL, " +
                IS_SELECTED + " TEXT NOT NULL); "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        this.onCreate(db);
    }

    //save data in DB
    public void save(Model model) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(POSITION,model.getPosition());
        cv.put(IS_SELECTED, model.getIsSelected());
        db.insert(DB_TABLE, null, cv);
        db.close();

    }
    //get All Data Array in DB
    public List<Model> getAllinform(String filter) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "";
        if (filter.equals("")) {
            query = "SELECT  * FROM " + DB_TABLE;
        }
        List<Model> list = new LinkedList<>();
        Cursor cursor = db.rawQuery(query, null);
        Model model;
        if (cursor.moveToFirst()) {
            do {
                model = new Model();
                model.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                model.setPosition(cursor.getInt(cursor.getColumnIndex(POSITION)));
                model.setIsSelected(cursor.getString(cursor.getColumnIndex(IS_SELECTED)));
                list.add(model);
            } while (cursor.moveToNext());
        }
        return list;
    }

}
