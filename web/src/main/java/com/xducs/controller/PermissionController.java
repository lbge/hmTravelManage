package com.xducs.controller;

import com.xducs.domain.Permission;
import com.xducs.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/9 - 9:18
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission) throws Exception {

        permissionService.save(permission);

        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission", permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deletePermission")
    public String deletePermission(Integer id) throws Exception {

        permissionService.deletePermissionById(id);

        return "redirect:findAll";
    }
}
