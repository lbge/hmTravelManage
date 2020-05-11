package com.xducs.controller;

import com.xducs.domain.Permission;
import com.xducs.domain.Role;
import com.xducs.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/9 - 8:53
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) Integer roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissionList = roleService.findOtherPermission(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId,
                                      @RequestParam(name = "permissionIds", required = true) Integer[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";
    }
    @RequestMapping("/deleteRole")
    public String deleteRole(@RequestParam(name = "id", required = true) Integer roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll";
    }
}