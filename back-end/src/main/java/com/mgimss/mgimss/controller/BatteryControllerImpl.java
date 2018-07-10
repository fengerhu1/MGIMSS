package com.mgimss.mgimss.controller;

import com.mgimss.mgimss.entity.Battery;
import com.mgimss.mgimss.entity.BatteryStatus;
import com.mgimss.mgimss.entity.User;
import com.mgimss.mgimss.repository.BatteryStatusRepository;
import com.mgimss.mgimss.repository.BattetyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BatteryControllerImpl implements BatteryController{

    @Autowired
    BatteryStatusRepository batteryStatusRepository;

    @Autowired
    BattetyRepository battetyRepository;

    //python calls
    public String post_remaining(String time, String remaining){

        User user;
        Battery battery;
        Date recordTime;
        Long remainingCharge;

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        user = (User) auth.getPrincipal();

        battery = battetyRepository.findByUser(user);

        if (battery == null){
            return "err: no such battery";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            recordTime = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return "err: encounter error when formatting date";
        }

        remainingCharge = Long.valueOf(remaining);

        //更新电池剩余电量
        battery.setRemain(remainingCharge);

        //记录log
        BatteryStatus batteryStatus = new BatteryStatus(remainingCharge, recordTime, battery);

        battetyRepository.save(battery);
        batteryStatusRepository.save(batteryStatus);

        return "success";
    }

    //java class
    public Long get_remaining(){

        User user;
        Battery battery;
        Long remainingCharge;

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        user = (User) auth.getPrincipal();

        battery = battetyRepository.findByUser(user);
        if (battery == null){
            return Long.valueOf(-1);
        }
        remainingCharge = battery.getRemain();

        return remainingCharge;
    }
}
