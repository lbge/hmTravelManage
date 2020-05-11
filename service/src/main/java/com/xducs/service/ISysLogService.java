package com.xducs.service;

import com.xducs.domain.SysLog;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/11 - 14:14
 */
public interface ISysLogService {
    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
