package com.example.yuan.androiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import model.User;

public class login extends Activity {
    private Button reg;
    private Button login;
    private EditText count;
    private EditText pwd;

    private List<User> userList;
    private List<User> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        reg= (Button) findViewById(R.id.reg);
        login= (Button) findViewById(R.id.login);
        count= (EditText) findViewById(R.id.username);
        pwd= (EditText) findViewById(R.id.password);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                //trim除去字符串前后的空字符
                User user=new User();
                if (name==null){
                    Toast.makeText(login.this,"用户名为空",Toast.LENGTH_SHORT).show();

                }else {
                    user.setUsername(name);
                    user.setUserpwd(pass);
                }

                int result= UserDAO.getInstance(getApplicationContext()).saveUser(user);
                if (result==1){

                    Toast.makeText(login.this, "〖注册成功〗",
                            Toast.LENGTH_SHORT).show();

                }else  if (result==-1)
                {

                    Toast.makeText(login.this, "〖用户名已经存在！〗",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                //userList=SqliteDB.getInstance(getApplicationContext()).loadUser();
                int result=UserDAO.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                    Intent intent = new Intent();
                    intent.setClass(login.this,MainActivity.class);
                    startActivity(intent);
                }
                else if (result==0){

                    Toast.makeText(login.this, "〖用户名不存在！〗",
                            Toast.LENGTH_SHORT).show();

                }
                else if(result==-1)
                {

                    Toast.makeText(login.this, "〖密码错误〗",
                            Toast.LENGTH_SHORT).show();
                }
/*                for (User user : userList) {

                    if (user.getUsername().equals(name))
                    {
                        if (user.getUserpwd().equals(pass))
                        {
                            state.setText("登录成功！");

                        }else {
                            state.setText("密码错误！");

                        }
                    }
                    else {
                        state.setText("用户名不存在！");

                    }

                }*/

            }
        });
    }
}


