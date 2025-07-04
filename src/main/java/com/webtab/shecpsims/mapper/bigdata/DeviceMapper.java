package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.Device;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备信息数据访问接口
 */
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
}
