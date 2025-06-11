
// src/main/java/com/webtab/shecpsims/service/impl/MedicinesServiceImpl.java
package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.model.entity.bigdata.Medicine;
import com.webtab.shecpsims.mapper.bigdata.MedicineMapper;
import com.webtab.shecpsims.service.bigdata.MedicinesService;
import org.springframework.stereotype.Service;

@Service
public class MedicinesServiceImpl extends ServiceImpl<MedicineMapper, Medicine> implements MedicinesService {
    // 如果需要扩展业务逻辑，可以在这里写
}
