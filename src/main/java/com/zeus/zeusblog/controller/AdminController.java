package com.zeus.zeusblog.controller;

import com.zeus.zeusblog.constant.UserScoreInfo;
import com.zeus.zeusblog.pojo.User;
import com.zeus.zeusblog.service.UserService;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AdminController
 * @Description 用户界面管理控制器
 * @Author ZEUS
 * @Date 2019/4/19 14:17
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    /**
     * @Author zeus
     * @Description 用户信息页
     * @Date 15:22 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/userinfo")
    public String userInfo(HttpServletRequest request, Model model){
       User userNow = getUserNow(request);

        //如果当前无用户登陆返回主页
        if (userNow == null)
            return "/login";

        //将当前登陆用户信息放入model对象
        userInfoTomodel(userNow,model);

        return "/admin-user";
    }

    @ResponseBody
    @RequestMapping("/edituser")
    public User editUser(HttpServletRequest request,
                         @RequestParam("user-name") String userName,
                         @RequestParam("user-email") String userEmail,
                         @RequestParam("user-phone") String userPhone,
                         @RequestParam("user-QQ") String userQQ,
                         @RequestParam("user-weibo") String userWeibo,
                         @RequestParam("user-intro") String userIntro){
        //获取参数
        User userNow = getUserNow(request);

        userNow.setUUserName(userName);
        userNow.setUEmail(userEmail);
        userNow.setUTelephone(userPhone);
        userNow.setUQQ(userQQ);
        userNow.setUWeibo(userWeibo);
        userNow.setUPersonalBrief(userIntro);

        //存入数据库
        if (userService.editUser(userNow)){
            //更新session中当前登陆用户信息
            request.getSession().setAttribute("userInfo",userNow);
        }

        return userNow;
    }

    /**
     * @Author zeus
     * @Description 帮助页
     * @Date 15:26 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/userhelp")
    public String userhelp(HttpServletRequest request, Model model){
        User userNow = getUserNow(request);
        //如果当前无用户登陆返回主页
        if (userNow == null)
            return "/login";

        //将当前登陆用户信息放入model对象
        userInfoTomodel(userNow,model);

        return "/admin-help";
    }

    /**
     * @Author zeus
     * @Description 用户相册页
     * @Date 15:26 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/usergallery")
    public String userGallery(HttpServletRequest request, Model model){
        User userNow = getUserNow(request);
        //如果当前无用户登陆返回主页
        if (userNow == null)
            return "/login";

        //将当前登陆用户信息放入model对象
        userInfoTomodel(userNow,model);

        return "/admin-gallery";
    }

    /**
     * @Author zeus
     * @Description 日志页
     * @Date 15:26 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/log")
    public String getLog(HttpServletRequest request, Model model){
        User userNow = getUserNow(request);
        //如果当前无用户登陆返回主页
        if (userNow == null)
            return "/login";

        //将当前登陆用户信息放入model对象
        userInfoTomodel(userNow,model);

        return "/admin-log";
    }

    /**
     * @Author zeus
     * @Description 404页
     * @Date 15:26 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/404")
    public String getNo(HttpServletRequest request, Model model){
        User userNow = getUserNow(request);
        //如果当前无用户登陆返回主页
        if (userNow == null)
            return "/login";

        //将当前登陆用户信息放入model对象
        userInfoTomodel(userNow,model);

        return "/admin-404";
    }

    /**
     * @Author zeus
     * @Description table页
     * @Date 15:26 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/table")
    public String getTable(HttpServletRequest request, Model model){
        User userNow = getUserNow(request);
        //如果当前无用户登陆返回主页
        if (userNow == null)
            return "/login";

        //将当前登陆用户信息放入model对象
        userInfoTomodel(userNow,model);

        return "/admin-table";
    }

    /**
     * @Author zeus
     * @Description form页
     * @Date 15:26 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/form")
    public String getForm(HttpServletRequest request, Model model){
        User userNow = getUserNow(request);
        //如果当前无用户登陆返回主页
        if (userNow == null)
            return "/login";

        //将当前登陆用户信息放入model对象
        userInfoTomodel(userNow,model);

        return "/admin-form";
    }




/**----------------------------------------------------------------------------------------------------------------------
     * @Author zeus
     * @Description 从请求 session中获取当前登陆用户
     * @Date 15:25 2019/4/19
     * @Param [request]
     * @return com.zeus.zeusblog.pojo.User
     **/
    private User getUserNow(HttpServletRequest request){
        User userNow = null;
        userNow = (User) request.getSession().getAttribute("userInfo");
        return userNow;
    }
    
    /**
     * @Author zeus
     * @Description 提取用户信息存入model对象中返回前台
     * @Date 13:54 2019/4/22
     * @Param [userNow, model]
     * @return void
     **/
    protected void userInfoTomodel(User userNow,Model model){
        //提取当前用户信息放入model对象中
        model.addAttribute("userName", userNow.getUUserName());
        model.addAttribute("uLevlel",userNow.getULevel());
        //判断用户当前等级返回不同详细等级信息
        switch (userNow.getULevel()){
            case 1:model.addAttribute("uDay", UserScoreInfo.LEVEL_ONE_SCORE - userNow.getUScore());
                   model.addAttribute("uDayAll",UserScoreInfo.LEVEL_ONE_SCORE);break;
            case 2:model.addAttribute("uDay",UserScoreInfo.LEVEL_TWO_SCORE - userNow.getUScore());
                   model.addAttribute("uDayAll",UserScoreInfo.LEVEL_TWO_SCORE);break;
            case 3:model.addAttribute("uDay",UserScoreInfo.LEVEL_THREE_SCORE - userNow.getUScore());
                   model.addAttribute("uDayAll",UserScoreInfo.LEVEL_THREE_SCORE);break;
            case 4:model.addAttribute("uDay",UserScoreInfo.LEVEL_FOUR_SCORE-userNow.getUScore());
                   model.addAttribute("uDayAll",UserScoreInfo.LEVEL_FOUR_SCORE);break;
            case 5:model.addAttribute("uDay",UserScoreInfo.LEVEL_SIX_SCORE-userNow.getUScore());
                   model.addAttribute("uDayAll",UserScoreInfo.LEVEL_SIX_SCORE);break;
            case 6:model.addAttribute("uDay",0); break;
        }
        model.addAttribute("uQQ",userNow.getUQQ());
        model.addAttribute("uTelephone",userNow.getUTelephone());
        model.addAttribute("uWeibo",userNow.getUWeibo());
        model.addAttribute("uEmail",userNow.getUEmail());
        model.addAttribute("uPersonalBrief",userNow.getUPersonalBrief());
    }


}
