package com.hopu.controller;


import com.hopu.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    // 用户登录
	@PostMapping("/user/login")
    public String login(User user, Model model,HttpServletRequest request){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            // 登录成功后，用户放在session与对象中
            HttpSession session = request.getSession();
            User user1 = (User) subject.getPrincipal();
            session.setAttribute("user",user1);
            return "admin/index";
        } catch (Exception e) {
            String msg = "账户["+ token.getPrincipal() + "]的用户名或密码错误！";
            model.addAttribute("msg", msg);
            return "forward:/login.jsp";
        }
	}
}
