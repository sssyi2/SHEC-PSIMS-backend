package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.mapper.bigdata.DevicesMapper;
import com.webtab.shecpsims.model.entity.bigdata.Devices;
import com.webtab.shecpsims.service.bigdata.DevicesService;
import org.springframework.stereotype.Service;

@Service
public class DevicesServiceImpl extends ServiceImpl<DevicesMapper, Devices> implements DevicesService {
    // 可以在这里扩展自定义业务方法
}
