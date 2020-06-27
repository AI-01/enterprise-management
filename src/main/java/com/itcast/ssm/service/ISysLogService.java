package com.itcast.ssm.service;

import com.itcast.ssm.domain.SysLog;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/27 - 10:44
 */
public interface ISysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
