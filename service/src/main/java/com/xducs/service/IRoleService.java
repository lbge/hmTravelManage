package com.xducs.service;

import com.xducs.domain.Permission;
import com.xducs.domain.Role;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/9 - 8:55
 */
public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(Integer id) throws Exception;

    List<Permission> findOtherPermission(Integer roleId) throws Exception;

    void addPermissionToRole(Integer roleId,Integer[] permissionIds) throws Exception;

    void deleteRoleById(Integer roleId) throws Exception;
}
