package com.z1s1c1.controller;

import com.z1s1c1.exception.AgeException;
import com.z1s1c1.exception.MyUserException;
import com.z1s1c1.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value ="/some.do")
    public ModelAndView doSome(String name,Integer age) throws MyUserException {
        ModelAndView mv = new ModelAndView();
        //根据请求参数抛出异常
        if (!"zs".equals(name)){
            throw new NameException("姓名不正确");
        }
        if (age==null||age>=80){
            throw new AgeException("年龄比较大了");
        }
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        mv.setViewName("show");
        return mv;
    }
}
