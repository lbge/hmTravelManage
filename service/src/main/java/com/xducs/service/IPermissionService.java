package com.xducs.service;

import com.xducs.domain.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/9 - 9:21
 */

public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    Permission findById(Integer id) throws Exception;

    void deletePermissionById(Integer id) throws Exception;
}
