package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.User;

public class UserDAO {

    public UserDAO(Context context) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    private static UserDAO sqliteDB;

    private SQLiteDatabase db;
    /**
     * 获取SqliteDB实例
     * @param context
     */
    public synchronized static UserDAO getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new UserDAO(context);
        }
        return sqliteDB;
    }

    /**
     * 将User实例存储到数据库。
     */
    public int  saveUser(User user) {
        if (user != null) {
           /* ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("userpwd", user.getUserpwd());
            db.insert("User", null, values);*/

            Cursor cursor = db.rawQuery("select * from User where username=?",
                    new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into User(username,userpwd) values(?,?) ", new String[]{user.getUsername().toString(), user.getUserpwd().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * 从数据库读取User信息。
     */
    public List<User> loadUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                user.setUserpwd(cursor.getString(cursor

                        .getColumnIndex("userpwd")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int Quer(String pwd,String name){


        HashMap<String,String> hashmap=new HashMap<String,String>();
        Cursor cursor =db.rawQuery("select * from User where username=?", new String[]{name});

        // hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        if (cursor.getCount()>0)
        {
            Cursor pwdcursor =db.rawQuery("select * from User where userpwd=? and username=?",new String[]{pwd,name});
            if (pwdcursor.getCount()>0)
            {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }
    }

    public User isuesrname(String username){
        User user=new User();
        Cursor cursor =  db.rawQuery("SELECT * FROM user WHERE username = ?",
                new String[]{username.toString()});
        if (cursor.getCount()>0){
            cursor.moveToFirst();// 将游标移动到第一条记录
            user.setUsername(cursor.getString(0));// 获得用户名的值然后进行设置
            user.setUserpwd(cursor.getString(1));// 获得密码的值然后进行设置
            return user;
        }
        cursor.close();// 关闭游标
        return null;
    }

    public void update(User user) {
//		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
        // 执行修改密码操作
        db.execSQL("update User set password = ?",
                new Object[] { user.getUserpwd() });
    }
}

