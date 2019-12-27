package com.example.sqliteprojectone;

import java.util.List;

public interface IUserDao {
    /**
     * 添加数据
     *
     * @param user
     * @return
     */
    long addUser(User user);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int delUserById(int id);

    /**
     * 更新数据
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查询数据
     *
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 列出数据
     *
     * @return
     */
    List<User> listUser();
}
