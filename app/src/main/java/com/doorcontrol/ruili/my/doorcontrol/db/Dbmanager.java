package com.doorcontrol.ruili.my.doorcontrol.db;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： Lyp
 * @创建时间：${date} ${time}
 * @备注：
 */
public class Dbmanager {

    private static final String TAG = "DbManager";

    private static Dbmanager sDbManager;

    private DbUtils mDb;

    public static Dbmanager getInstance() {
        return sDbManager;
    }

    public static void init(Context context) {
        sDbManager = new Dbmanager(context);
    }

    Dbmanager(Context context) {
        mDb = DbUtils.create(context, "idoor.db", 1, new DbUtils.DbUpgradeListener() {

            @Override
            public void onUpgrade(DbUtils arg0, int arg1, int arg2) {
                // Nothing now

            }
        }); // null for


    }

    public DbUtils getDb() {
        return mDb;
    }
}