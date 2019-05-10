package com.zeus.zeusblog.dao;

import com.zeus.zeusblog.pojo.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author ZEUS
 * @Date 2019/4/12 10:09
 * @Version 1.0
 */
public interface UserDao {

    //获取所有用户
    List<User> getAllUsers();
    //根据uuid获取用户
    User getUserByUuid(String uuid);
    //根据loginname与password登陆
    User getUserByLogin(String loginname,String password) throws Exception;
    //创建新用户
    int createUser(User user);
    //删除用户
    int deleteUser(String uuid);
    //编辑用户
    int editUser(User userNew);


    //更改用户最近登陆时间
    int updateLatestTime(User user, String date) throws Exception;

    //积分相关方法
    //增加用户积分
    int increaseScore(User user,int score);

    //增加用户积分
    int clearScore(User user);

    //提升用户等级
    int increaseLeverl(User user,int level);


}
