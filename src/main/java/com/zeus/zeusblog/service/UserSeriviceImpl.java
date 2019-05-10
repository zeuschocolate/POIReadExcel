package com.zeus.zeusblog.service;

import com.zeus.zeusblog.dao.UserDao;
import com.zeus.zeusblog.dao.UserDaoImpl;
import com.zeus.zeusblog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName UserSeriviceImpl
 * @Description TODO
 * @Author ZEUS
 * @Date 2019/4/12 10:20
 * @Version 1.0
 */
@Service("userService")
public class UserSeriviceImpl implements UserService {

    @Autowired
    UserDao userDao = new UserDaoImpl();

    /**
     * @Author zeus
     * @Description 登陆用户
     * @Date 13:27 2019/4/12
     * @Param [loginname, password]
     * @return com.zeus.zeusblog.pojo.User
     **/
    @Override
    public User getUserByLogin(String loginname, String password) throws Exception {
        User user = null;
        user = userDao.getUserByLogin(loginname,password);
        return user;
    }

    /**
     * @Author zeus
     * @Description 根据uuid获取用户
     * @Date 10:22 2019/4/12
     * @Param [uuid]
     * @return com.zeus.zeusblog.pojo.User
     **/
    @Override
    public User getUserByUuid(String uuid) {
        User user;
        user = userDao.getUserByUuid(uuid);
        return user;
    }

    /**
     * @Author zeus
     * @Description 用户下线时更新用户最近登陆时间
     * @Date 16:37 2019/4/19
     * @Param [userNow, dateNow]
     * @return boolean
     **/
    @Override
    public boolean updateLatestTime(User userNow, String dateNow) throws Exception {
        int result = userDao.updateLatestTime(userNow,dateNow);
        if (result>0)
            return true;
        return false;
    }


    /**
     * @Author zeus
     * @Description 用户上线更新用户积分与等级
     * @Date 9:31 2019/4/22
     * @Param [user]
     * @return boolean
     **/
    @Override
    public boolean updateLevelAndScore(User user) {
        int uLevel;
        int uScore;
        Date date = new Date();
        String uLatestTime;

        //获取当前时间与用户最近登陆时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式,此处无需时分秒
        String dataNow = df.format(date);
        uLatestTime = user.getULatestTime();

        //判断用户当前登陆时间是否与最近登陆时间为同一天,不为一天则积分加1
        if (!dataNow.equals(uLatestTime)){

            userDao.increaseScore(user,1);

            //从当前登陆用户信息中获取用户更改后积分及等级信息
            uLevel = user.getULevel();
            uScore = user.getUScore();

            switch (uLevel){
                //等级1需登陆三天后升级
                case 1:if (uScore>=3){
                    userDao.increaseLeverl(user,1);
                    //提升等级后清空当前积分
                    userDao.clearScore(user);
                }
                //等级2需登陆7天后升级
                case 2:if (uScore>=7){
                    userDao.increaseLeverl(user,1);
                    //提升等级后清空当前积分
                    userDao.clearScore(user);
                }
                //等级3需登陆30天后升级
                case 3:if (uScore>=30){
                    userDao.increaseLeverl(user,1);
                    //提升等级后清空当前积分
                    userDao.clearScore(user);
                }
                //等级4需登陆90天后升级
                case 4:if (uScore>=90){
                    userDao.increaseLeverl(user,1);
                    //提升等级后清空当前积分
                    userDao.clearScore(user);
                }
                //等级5需登陆365天后升级
                case 5:if (uScore>=365){
                    userDao.increaseLeverl(user,1);
                    //提升等级后清空当前积分
                    userDao.clearScore(user);
                }
                case 6:break;

            }
            return true;
        }else {
            return false;
        }

    }


    /**
     * @Author zeus
     * @Description 编辑用户部分基本信息
     * @Date 8:48 2019/4/23
     * @Param [userNow]
     * @return boolean
     **/
    @Override
    public boolean editUser(User userNow) {
        int result = userDao.editUser(userNow);
        return result>0;
    }
}
