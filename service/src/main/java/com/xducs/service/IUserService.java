package com.xducs.service;

import com.xducs.domain.Role;
import com.xducs.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/8 - 10:58
 */
public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(Integer userId) throws Exception;

    List<Role> findOtherRole(Integer userId) throws Exception;

    void addRoleToUser(Integer userId, Integer[] roleIds) throws Exception;
}
