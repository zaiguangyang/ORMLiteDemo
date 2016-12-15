package com.zhuoxin.dbdemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zhuoxin.dbdemo.model.RepoGroup;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/12/14.
 */

public class DBHelp extends OrmLiteSqliteOpenHelper {
    private static DBHelp dbHelp;
    private Context context;

    private DBHelp(Context context) {
        //提高版本号以更新数据
        super(context, "Repo", null, 2);
        this.context = context;
    }

    public static synchronized DBHelp getInstance(Context context) {
        if (dbHelp == null) {
            dbHelp = new DBHelp(context.getApplicationContext());
        }
        return dbHelp;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //创建表
            TableUtils.createTableIfNotExists(connectionSource, RepoGroup.class);
            // 添加本地默认数据进去(因为之前已经添加过数据,所以要更新数据,将版本号升1)
            new RepoGroupDao(this).createOrUpdate( RepoGroup.getDefaultGroup(context) );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //更新表(先删除旧表,在调用创建表的方法)
            TableUtils.dropTable(connectionSource, RepoGroup.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
