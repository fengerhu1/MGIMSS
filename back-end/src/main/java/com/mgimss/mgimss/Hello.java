package com.mgimss.mgimss;
import com.mgimss.mgimss.classes.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Hello{

    @RequestMapping("/")
    public String hello(){
        return "hello world.";
    }
}