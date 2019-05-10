package com.zeus.zeusblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    String uUuid;
    String uUserName;
    String uLoginName;
    String uPassWord;
    int uLevel;
    String uPicUrl;
    String uBirthday;
    String uEmail;
    String uTelephone;
    String uQQ;
    String uWeibo;
    String uLatestTime;
    int uScore;
    String uPersonalBrief;
}
