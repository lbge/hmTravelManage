package com.xducs.service.impl;

import com.xducs.dao.IRoleDao;
import com.xducs.domain.Permission;
import com.xducs.domain.Role;
import com.xducs.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/9 - 8:55
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findOtherPermission(Integer roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId,Integer[] permissionIds) throws Exception {
        for (int permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void deleteRoleById(Integer roleId) throws Exception {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }

}
