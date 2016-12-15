package com.zhuoxin.dbdemo.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
@DatabaseTable(tableName = "repo_group")
public class RepoGroup {
    //主键
    @DatabaseField(id = true)
    private int id;
    //条目名
    @DatabaseField(columnName = "NAME")
    private String name;

    //为了测试时能显示内容
    @Override
    public String toString() {
        return "RepoGroup{id=" + id + ", name=" + name + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static List<RepoGroup> repoGroupList;

    public static List<RepoGroup> getDefaultGroup(Context context) {
        if (repoGroupList != null) {
            return repoGroupList;
        }
        try {
            // 读取文件
            InputStream inputStream = context.getAssets().open("repogroup.json");
            // 使用IOUTILS包
            String content = IOUtils.toString(inputStream);
            Gson gson = new Gson();
            // 将json内容数据，转为实体数据，放到了集合中
            repoGroupList = gson.fromJson(content, new TypeToken<List<RepoGroup>>() {
            }.getType());
            return repoGroupList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
