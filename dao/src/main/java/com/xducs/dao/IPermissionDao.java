package com.xducs.dao;

import com.xducs.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/8 - 21:08
 */
public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId =#{id})")
    List<Permission> findPermissionByRoleId(Integer id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    @Select("select * from permission where id =#{id}")
    Permission findById(Integer id) throws Exception;

    @Delete("delete from role_permission where permissionId = #{id}")
    void deleteFromRole_Permission(Integer id) throws Exception;

    @Delete("delete from permission where id = #{id}")
    void deletePermissionById(Integer id) throws Exception;
}
