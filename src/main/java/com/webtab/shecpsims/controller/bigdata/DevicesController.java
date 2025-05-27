package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.model.entity.bigdata.Devices;
import com.webtab.shecpsims.service.bigdata.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DevicesController {

    @Autowired
    private DevicesService devicesService;

    @GetMapping
    public List<Devices> getAllDevices() {
        return devicesService.list();
    }

    @GetMapping("/{id}")
    public Devices getDeviceById(@PathVariable Integer id) {
        return devicesService.getById(id);
    }

    @PostMapping
    public boolean addDevice(@RequestBody Devices device) {
        return devicesService.save(device);
    }

    @PutMapping
    public boolean updateDevice(@RequestBody Devices device) {
        return devicesService.updateById(device);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDevice(@PathVariable Integer id) {
        return devicesService.removeById(id);
    }
}
