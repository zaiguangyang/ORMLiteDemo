package com.zhuoxin.dbdemo.dao;

import com.j256.ormlite.dao.Dao;
import com.zhuoxin.dbdemo.model.RepoGroup;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class RepoGroupDao {
    private Dao<RepoGroup, Long> dao;

    public RepoGroupDao(DBHelp dbHelp) {
        // getDao方法，用来构建指定实体的Dao文件
        // dao是对哪一个表操作？
        // 这个表的id是什么类型？
        try {
            dao = dbHelp.getDao(RepoGroup.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //添加所有数据的方法
    public void createOrUpdate(List<RepoGroup> list) {
        for (RepoGroup repo : list) {
            createOrUpdate(repo);
        }
    }

    //用来对repoGroup表进行更新添加
    public void createOrUpdate(RepoGroup repoGroup) {
        try {
            dao.createOrUpdate(repoGroup);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //用来查询所有数据
    public List<RepoGroup> queryForAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //根据id用来查询单条数据
    public RepoGroup queryForId(long id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
