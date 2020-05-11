package com.xducs.service.impl;

import com.xducs.dao.IPermissionDao;
import com.xducs.domain.Permission;
import com.xducs.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/9 - 9:22
 */
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(Integer id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deletePermissionById(Integer id) throws Exception {
        permissionDao.deleteFromRole_Permission(id);
        permissionDao.deletePermissionById(id);
    }
}
