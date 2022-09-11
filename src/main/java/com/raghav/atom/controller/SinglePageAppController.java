package com.raghav.atom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SinglePageAppController {
    @RequestMapping(value = {"/","/photography/**","/art/**"}, method = RequestMethod.GET)
    public String index(){
        return "forward:/index.html";
    }
}
