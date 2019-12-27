package com.example.sqliteprojectone;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private User user;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        userDao = new UserDao(this);
        user = new User();
        user.set_id(10018);
        user.setUsername("李四");
        user.setPassWord("0400");
        user.setAge(15);
        user.setSex("男");
        userDao.addUser(user);
        List<User> list = userDao.listUser();
        for (int i = 0; i < list.size(); i++) {
            textView.setText(
                             list.get(i).getAge() + " "+
                             list.get(i).getUsername()+" "+
                             list.get(i).getPassWord());
        }
    }
    /**
     * @description:增加数据的方法
     * @author: CLJZ
     * @date: 2019/12/5  10:28
     * @param: [user, id, username, password, sex, age]
     * @return: void
     */


    private void init() {
        textView = findViewById(R.id.show);
    }
}
