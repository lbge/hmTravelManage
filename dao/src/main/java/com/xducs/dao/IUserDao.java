package com.xducs.dao;

import com.xducs.domain.Role;
import com.xducs.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/8 - 11:04
 */
public interface IUserDao {
    /**
     * 根据username查找对象
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.xducs.dao.IRoleDao.findRoleByUserId")),
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id =#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.xducs.dao.IRoleDao.findRoleByUserId")),
    })
    UserInfo findById(Integer id) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRole(Integer userId) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId) throws Exception;
}
