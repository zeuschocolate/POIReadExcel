package com.zeus.zeusblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class UserMapper implements RowMapper<User> {

    /**
     * @Author zeus
     * @Description //TODO
     * @Date 9:52 2019/4/12
     * @Param [resultSet, i]
     * @return com.zeus.zeusblog.pojo.User
     **/
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        //从jdbc返回结果集中获取字段赋给对象
        String uUuid = resultSet.getString("uUuid");
        String uUserName = resultSet.getString("uUserName");
        String uLoginName = resultSet.getString("uLoginName");
        String uPassWord = resultSet.getString("uPassWord");
        int uLevel = resultSet.getInt("uLevel");
        String uPicUrl = resultSet.getString("uPicUrl");
        String uBirthday = resultSet.getString("uBirthday");
        String uEmail = resultSet.getString("uEmail");
        String uTelephone = resultSet.getString("uTelephone");
        String uQQ = resultSet.getString("uQQ");
        String uWeibo = resultSet.getString("uWeibo");
        String uLatestTime = resultSet.getString("uLatestTime");
        int uScore = resultSet.getInt("uScore");
        String uPersonalBrief = resultSet.getString("uPersonalBrief");

        User user = new User();
        user.setUUuid(uUuid);user.setUUserName(uUserName);
        user.setULoginName(uLoginName);user.setUPassWord(uPassWord);
        user.setULevel(uLevel);user.setUBirthday(uBirthday);user.setUPicUrl(uPicUrl);
        user.setUEmail(uEmail);user.setULatestTime(uLatestTime);
        user.setUScore(uScore);user.setUPersonalBrief(uPersonalBrief);
        user.setUQQ(uQQ);user.setUTelephone(uTelephone);user.setUWeibo(uWeibo);

        return user;
    }
}
