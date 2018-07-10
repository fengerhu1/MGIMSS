package com.light.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

//    @CrossOrigin
//    @RequestMapping(path = {"/", "/index", "/home"}, method = RequestMethod.GET)
//    public String root(){
//        return "welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//
//    @RequestMapping("/main")
//    public String main(){
//        return "/main/main";
//    }

    @RequestMapping(value = "/{any}", method = RequestMethod.GET)
    public String jump1(@PathVariable(value = "any") String any) {
        return any;
    }
}



