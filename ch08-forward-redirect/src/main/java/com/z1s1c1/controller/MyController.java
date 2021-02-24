package com.z1s1c1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    /*
    * 处理器方法返回ModelAndView实现转发forward
    * 语法：setViewName("forward:视图文件完整路径")
    * forward特点：不和视图解析器一同使用，就当项目中没有视图解析器
    * */
    @RequestMapping(value ="/doForward.do")
    public ModelAndView doWithForward(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的doSome方法");
        mv.setViewName("forward:/WEB-INF/view/show.jsp");
        return mv;
    }

    /*
    * 处理器方法返回ModelAndView，实现重定向redirect
    * 语法：setViewName("redirect:视图完整路径")
    * redirect特点：不和视图解析器一同使用，就当项目中没有视图解析器
    *
    * 框架对重定向的操作：
    *   1.框架会吧Model中的简单类型的数据，转为String使用，作为hello.jsp的get请求参数使用
    *   目的是在doRedirect.do和hello.jsp两次请求之间可以传递数据
    *   2.在目标hello.jsp页面可以使用参数结合对象${param}获取请求参数值
    *   ${param.myname}
    *   3.重定向不能访问/WEB-INF资源
    * */
    @RequestMapping(value ="/doRedirect.do")
    public ModelAndView doWithRedirect(String name,Integer age){
        ModelAndView mv = new ModelAndView();
        //数据放入到request作用域
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        //重定向
        mv.setViewName("redirect:/hello.jsp");
        return mv;
    }
}
