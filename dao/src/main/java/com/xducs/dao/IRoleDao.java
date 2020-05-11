package com.xducs.dao;

import com.xducs.domain.Permission;
import com.xducs.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/8 - 15:36
 */
public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.xducs.dao.IPermissionDao.findPermissionByRoleId")),

    })
    List<Role> findRoleByUserId(Integer userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.xducs.dao.IPermissionDao.findPermissionByRoleId")),

    })
    Role findById(Integer id) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId =#{roleId})")
    List<Permission> findOtherPermission(Integer roleId) throws Exception;

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId}) ")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId) throws Exception;


    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(Integer roleId) throws Exception;

    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(Integer roleId) throws Exception;

    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(Integer roleId) throws Exception;
}
