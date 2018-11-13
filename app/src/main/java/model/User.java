package model;

import android.content.Context;

public class User {
    private int id;
    private String username;
    private String userpwd;


    public User(int id, String string, String username){}

    public User(){
        super();
        this.username=username;
        this.userpwd=userpwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }


}
