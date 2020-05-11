package com.xducs.dao;

import com.xducs.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author Linbo Ge
 * @date 2020/5/7 - 13:38
 */
public interface IMemberDao {
    @Select("select * from member where id = #{id}")
    Member findById(Integer id) throws Exception;
}
