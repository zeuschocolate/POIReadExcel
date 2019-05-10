package com.zeus.zeusblog.controller;

import com.zeus.zeusblog.constant.UserLevel;
import com.zeus.zeusblog.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zeus
 * @Description 主页跳转控制
 * @Date 13:40 2019/4/15
 **/
@Controller
@RequestMapping("/zeusblog")
public class IndexController {

    /**
     * @Author zeus
     * @Description 判断跳转用户着陆页
     * @Date 13:55 2019/4/15
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/index")
    public String toIndex(HttpServletRequest request, Model model){

        User userInfo = new User();

        //从session中判断是否有用户登陆
        userInfo = (User)request.getSession().getAttribute("userInfo");
        if (userInfo != null){
            //有用户信息跳转至对应用户权限的主页面
            if (userInfo.getULevel() == UserLevel.ADMIN){
                model.addAttribute("userName",userInfo.getUUserName());

                return "/admin-index";
            }

            if(userInfo.getULevel() == UserLevel.COMMON_USER){
                model.addAttribute("userName",userInfo.getUUserName());

                return "/admin-index";
            }
        }
        //无用户信息返回登陆页面
        return "/login";
    }
}
