package com.xducs.controller;

import com.xducs.domain.Role;
import com.xducs.domain.UserInfo;
import com.xducs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/8 - 18:12
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @PreAuthorize("authentication.principal.username == 'tom'")
    @RequestMapping("/save")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show1");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) Integer userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> roleList = userService.findOtherRole(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) Integer userId,
                                @RequestParam(name = "ids", required = true) Integer[] roleIds) throws Exception {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll";
    }
}
