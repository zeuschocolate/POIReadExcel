package com.zeus.zeusblog.dao;

import com.zeus.zeusblog.pojo.User;
import com.zeus.zeusblog.pojo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author ZEUS
 * @Date 2019/4/12 10:11
 * @Version 1.0
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Author zeus
     * @Description 获取所有用户
     * @Date 10:11 2019/4/12
     * @Param []
     * @return java.util.List<com.zeus.zeusblog.pojo.User>
     **/
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user;";
        List<User> userList = jdbcTemplate.query(sql, new UserMapper());
        return userList;
    }

    /**
     * @Author zeus
     * @Description 根据uuid查询用户信息
     * @Date 10:15 2019/4/12
     * @Param [uuid]
     * @return com.zeus.zeusblog.pojo.User
     **/
    @Override
    public User getUserByUuid(String uuid) {
        User user;
        String sql = "SELECT * FROM user WHERE uUuid = ?;";
        user = jdbcTemplate.queryForObject(sql,new UserMapper(),uuid);
        return user;
    }

    /**
     * @Author zeus
     * @Description 确认用户
     * @Date 13:23 2019/4/12
     * @Param [loginname, password]
     * @return com.zeus.zeusblog.pojo.User
     **/
    @Override
    public User getUserByLogin(String loginname, String password) throws Exception {
        User user;
        String sql = "SELECT * FROM user WHERE uLoginName = ? AND uPassword = ?;";
        user = jdbcTemplate.queryForObject(sql,new UserMapper(),loginname,password);
        return user;
    }

    
    /**
     * @Author zeus
     * @Description 创建用户
     * @Date 16:16 2019/4/19
     * @Param [user]
     * @return int
     **/
    @Override
    public int createUser(User user) {
        return 0;
    }

    /**
     * @Author zeus
     * @Description 根据uuid删除用户
     * @Date 16:16 2019/4/19
     * @Param [uuid]
     * @return int
     **/
    @Override
    public int deleteUser(String uuid) {
        return 0;
    }

    /**
     * @Author zeus
     * @Description 编辑用户
     * @Date 15:46 2019/4/22
     * @Param [userNew]
     * @return int
     **/
    @Override
    public int editUser(User userNew) {
        String uuid = userNew.getUUuid();
        int result;
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE user SET");
        sql.append(" uUserName=?,uEmail=?,uTelephone=?,uQQ=?,uWeibo=?,uPersonalBrief=?");
        sql.append(" WHERE uUuid = ?");

        result = jdbcTemplate.update(sql.toString(),userNew.getUUserName(),userNew.getUEmail(),userNew.getUTelephone()
                            ,userNew.getUQQ(),userNew.getUWeibo(),userNew.getUPersonalBrief(),userNew.getUUuid());

        return result;
    }

    /**
     * @Author zeus
     * @Description 更新用户最近登陆时间
     * @Date 16:17 2019/4/19
     * @Param [user]
     * @return int
     **/
    @Override
    public int updateLatestTime(User user, String date) throws Exception{
        String uUuid = user.getUUuid();
        int result;
        String sql = "UPDATE user SET uLatestTime = ? WHERE uUuid = ?;";
        result = jdbcTemplate.update(sql,date,uUuid);
        return result;
    }

    /**
     * @Author zeus
     * @Description 用户增加积分
     * @Date 16:17 2019/4/19
     * @Param [user, score]
     * @return int
     **/
    @Override
    public int increaseScore(User user, int score) {
        String uUuid = user.getUUuid();
        int result;
        String sql = "UPDATE user SET uScore = uScore+? WHERE uUuid = ?;";
        result = jdbcTemplate.update(sql,score,uUuid);
        return result;
    }

    /**
     * @Author zeus
     * @Description 清除用户当前积分为0
     * @Date 9:53 2019/4/22
     * @Param [user]
     * @return int
     **/
    @Override
    public int clearScore(User user) {
        String uUuid = user.getUUuid();
        int result;
        String sql = "UPDATE user SET uScore = 0 WHERE uUuid = ?;";
        result = jdbcTemplate.update(sql,uUuid);
        return result;
    }

    /**
     * @Author zeus
     * @Description 用户提升等级
     * @Date 9:49 2019/4/22
     * @Param [user, level]
     * @return int
     **/
    @Override
    public int increaseLeverl(User user, int level) {
        String uUuid = user.getUUuid();
        int result;
        String sql = "UPDATE user SET uLevel = uLevel+? WHERE uUuid = ?;";
        result = jdbcTemplate.update(sql,level,uUuid);
        return result;
    }
}
