package com.z1s1c1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
* @RequestMapping
*   value:所有请求地址的公共部分
*   位置:放在类的上面
* */
@Controller
@RequestMapping("/user")
public class MyController {
    /*
    *   @RequestMapping:请求映射
    *   属性:method,表示请求的方式;它的值RequestMapping类枚举值;
    *               get请求方式，RequestMapping.GET
    *               post请求方式，RequestMapping.POST
    *
    * */

    //指定some.do使用get请求方式
    @RequestMapping(value ="/some.do",method = RequestMethod.GET)
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的doSome方法");
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping(value = "/other.do",method = RequestMethod.POST)
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","----欢迎使用springmvc做web开发----");
        mv.addObject("fun","执行的doOther方法");
        mv.setViewName("other");
        return mv;
    }

    //不指定请求方式，没有限制
    @RequestMapping(value = "/first.do")
    public ModelAndView doFirst(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","----欢迎使用springmvc做web开发----"+request.getParameter("name"));
        mv.addObject("fun","执行的doFirst方法");
        mv.setViewName("other");
        return mv;
    }
}
