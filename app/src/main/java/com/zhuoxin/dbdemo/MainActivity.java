package com.zhuoxin.dbdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zhuoxin.dbdemo.dao.DBHelp;
import com.zhuoxin.dbdemo.dao.RepoGroupDao;
import com.zhuoxin.dbdemo.model.RepoGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //测试方法
    public void getDB() {
        DBHelp dbHelp = DBHelp.getInstance(this);
        RepoGroupDao repoGroupDao = new RepoGroupDao(dbHelp);
        for (int i = 0; i < 10; i++) {
            RepoGroup repoGroup = new RepoGroup();
            repoGroup.setId(i + 1);
            repoGroup.setName("类别" + i);
            repoGroupDao.createOrUpdate(repoGroup);
        }
        Log.d("TAG", repoGroupDao.queryForAll().toString());
        Log.d("TAG", repoGroupDao.queryForId(5).toString());
    }
}
