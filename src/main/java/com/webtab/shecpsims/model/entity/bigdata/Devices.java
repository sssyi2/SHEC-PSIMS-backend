package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * (Devices)实体类
 *
 * @author makejava
 * @since 2025-05-16 10:32:42
 */
@Data
@ToString
@TableName("devices")
public class Devices implements Serializable {
    private static final long serialVersionUID = 781685101165175703L;

    @TableId(value = "device_id", type = IdType.AUTO)
    private Integer deviceId;

    private String deviceCode;

    private String deviceName;

    private String deviceType;

    private String status;

    private String location;

    private Date lastMaintenance;

}

