package com.example.yuan.androiddemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.PwdDAO;
import dao.UserDAO;
import model.Tb_pwd;
import model.User;

public class Sysset extends Activity {
    EditText txtpwd,txtpwd1;// 创建EditText对象
    Button btnSet, btnsetCancel;// 创建两个Button对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sysset);// 设置布局文件

        txtpwd = (EditText) findViewById(R.id.txtPwd);// 获取密码文本框
        txtpwd1 = (EditText)findViewById(R.id.txtPwd1);
        btnSet = (Button) findViewById(R.id.btnSet);// 获取设置按钮
        btnsetCancel = (Button) findViewById(R.id.btnsetCancel);// 获取取消按钮

        btnSet.setOnClickListener(new View.OnClickListener() {// 为设置按钮添加监听事件
            @Override
            public void onClick(View arg0) {

                String pwd = txtpwd.getText().toString();// 获取新密码
                String pwd1 = txtpwd1.getText().toString();// 获取旧密码
                User user = new User();
                UserDAO userDAO = new UserDAO(Sysset.this);
                if(pwd.equals(pwd1)){
                    Toast.makeText(Sysset.this,"新密码与旧密码相同",Toast.LENGTH_SHORT).show();
                }else{
                    user.setUserpwd(pwd1);
                    userDAO.update(user);
                    Toast.makeText(Sysset.this,"修改成功",Toast.LENGTH_SHORT).show();
                }


//                if (pwdDAO.getCount() == 0) {// 判断数据库中是否已经设置了密码
//                    pwdDAO.add(tb_pwd1);// 添加用户密码
//                } else {
//                    pwdDAO.update(tb_pwd1);// 修改用户密码
//                }
//                // 弹出信息提示
//                Toast.makeText(Sysset.this, "〖密码〗修改成功！", Toast.LENGTH_SHORT)
//                        .show();
            }
        });

        btnsetCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                txtpwd.setText("");// 清空密码文本框
                txtpwd.setHint("请输入旧密码");// 为密码文本框设置提示
                txtpwd1.setText("");// 清空密码文本框
                txtpwd1.setHint("请输入新密码");// 为密码文本框设置提示
            }
        });
    }
}
