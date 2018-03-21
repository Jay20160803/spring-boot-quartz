package com.jk51.mapper;


import com.jk51.model.ScheduleMeta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 版权所有(C) 2017 上海银路投资管理有限公司
 * 描述: ScheduleMetaMapper
 * 作者: wangzhengfei
 * 创建日期: 2017-02-22
 * 修改记录:
 */

@Mapper
public interface ScheduleMetaMapper {


    List<ScheduleMeta> queryAll(Integer enabled);

    ScheduleMeta queryOne(Integer id);

    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);

}
