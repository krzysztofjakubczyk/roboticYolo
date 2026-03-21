package com.sanbot.demo.manager;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sanbot.demo.entity.SQLParam;
import com.sanbot.net.NetApi;

import java.util.Locale;

/**
 * 数据库
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;

    private static DBHelper mInstance;

    public static DBHelper getInstance(Context context) {
        if (null == mInstance) {
            synchronized (DBHelper.class) {
                if (null == mInstance)
                    mInstance = new DBHelper(context.getApplicationContext());
            }
        }
        return mInstance;
    }

    private DBHelper(Context context) {
        this(context, String.format(Locale.getDefault(), SQLParam.FILE_NAME, NetApi.getInstance().onGetUID()), DB_VERSION);
    }

    private DBHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, factory, version, null);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLParam.Friend.CREATE);
        db.execSQL(SQLParam.Chat.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(SQLParam.Friend.DELETE);
            db.execSQL(SQLParam.Friend.CREATE);

            db.execSQL(SQLParam.Chat.DELETE);
            db.execSQL(SQLParam.Chat.CREATE);
        }

    }

}
