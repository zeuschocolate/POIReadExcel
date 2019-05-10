package com.zeus.zeusblog.service;

import com.zeus.zeusblog.dao.UserDao;
import com.zeus.zeusblog.dao.UserDaoImpl;
import com.zeus.zeusblog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author ZEUS
 * @Date 2019/4/12 10:00
 * @Version 1.0
 */
@Repository
public interface UserService {

    //根据uuid获取用户
    User getUserByUuid (String uuid);
    //登陆用户
    User getUserByLogin(String loginname,String password) throws Exception;
    //用户下线更改用户最近登陆时间
    boolean updateLatestTime(User userNow,String dateNow) throws Exception;
    //用户上线更新用户积分等级信息
    boolean updateLevelAndScore(User user);
    //更改当前用户部分个人信息
    boolean editUser(User userNow);
}
