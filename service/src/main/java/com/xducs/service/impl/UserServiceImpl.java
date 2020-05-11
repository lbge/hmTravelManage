package com.xducs.service.impl;

import com.xducs.dao.IUserDao;
import com.xducs.domain.Role;
import com.xducs.domain.UserInfo;
import com.xducs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/8 - 10:59
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //自己的用户对象封装成UserDetail对象
        if (userInfo != null) {
            User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                    userInfo.getStatus() != 0, true, true, true,
                    getAuthority(userInfo.getRoles()));

            return user;
        }
        return null;
    }

    //返回一个list集合，集合中是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(Integer userId) throws Exception {
        return userDao.findById(userId);
    }

    @Override
    public List<Role> findOtherRole(Integer userId) throws Exception {
        return userDao.findOtherRole(userId);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) throws Exception {
        for (Integer roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
