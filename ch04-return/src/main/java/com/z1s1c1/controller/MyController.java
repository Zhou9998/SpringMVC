package com.z1s1c1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.z1s1c1.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
    /*
    * 处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
    * */
    @RequestMapping(value ="/returnString.do")
    public String doReturnView(HttpServletRequest request ,String name, Integer age){
        //show:逻辑视图名称，项目中配置了视图解析器
        //框架对视图执行forward转发操作
        request.setAttribute("myname",name);
        request.setAttribute("myage",age);
        return "show";
    }

    //处理器方法返回String，表示完整视图路径，此时不能配置视图解析器
    @RequestMapping(value ="/returnString2.do")
    public String doReturnView2(HttpServletRequest request ,String name, Integer age){
        request.setAttribute("myname",name);
        request.setAttribute("myage",age);
        //完整视图路径，项目中不能配置视图解析器
        //框架对视图执行forward转发操作
        return "/WEB-INF/view/show.jsp";
    }

    //处理器方法返回void，响应ajax请求
    //手工实现ajax，json数据：代码有重复的 1.java对象转为json；2.通过HttpServletResponse输出json数据
    @RequestMapping(value ="/returnVoid-ajax.do")
    public void doReturnVoidAjax(HttpServletResponse response, String name, Integer age) throws IOException {
        System.out.println("====doReturnVoidAjax====,name="+name+"  age="+age);
        //处理ajax，使用json做数据的格式
        //service调用完成了，使用Student表示处理结果
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        String json = "";
        //把结果的对象转为json格式的数据
        if (student != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(student);
            System.out.println("student转化的json===="+json);
        }

        //输出数据，相应ajax的请求
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }

    /*
    * 处理器方法返回一个Student，通过框架转为json，响应ajax请求
    * @ResponseBody:
    *   作用：把处理器方法返回对象转为json后，通过HttpServletResponse输出给浏览器
    *   位置：方法的定义上面。和其他注解没有顺序的关系。
    * 返回对象框架的处理流程：
    *   1.框架会把返回student类型，调用框架中的ArrayList<HttpMessageConverter中每个类的canWriter()方法
    *       检查哪个HttpMessageConverter接口的实现类能处理Student类型的数据---MappingJackson2HttpMessageConverter
    *   2.框架会调用实现类的write(),MappingJackson2HttpMessageConverter的write()方法
    *       把lisi的Student对象转为json，调用Jackson的ObjectMapper实现转为json
    *   3.框架会调用@ResponseBody把2的结果数据输出到浏览器，ajax请求处理完成
    * */
    @RequestMapping(value ="/returnStudentJson.do")
    @ResponseBody
    public Student doStudentJsonObject(String name, Integer age){
        //调用service，获取请求结果数据，student对象表示结果数据
        Student student = new Student();
        student.setName("lisi");
        student.setAge(20);
        return student;
    }
    /*
    * 处理器方法返回List<Student>
    * */
    @RequestMapping(value ="/returnStudentJsonArray.do")
    @ResponseBody
    public List<Student> doStudentJsonObjectArray(String name, Integer age){
        List<Student> list = new ArrayList<>();
        //调用service，获取请求结果数据，student对象表示结果数据
        Student student = new Student();
        student.setName("lisi");
        student.setAge(20);
        list.add(student);
        student = new Student();
        student.setName("zhangsan");
        student.setAge(18);
        list.add(student);
        return list;
    }

    /*
    * 处理器方法返回的String，String表示数据的，不是视图
    * 区分返回值String是数据还是视图，看有没有@ResponseBody注解
    * 如果有@ResponseBody注解，返回String就是数据，反之就是视图
    *
    * 默认使用"text/plain;char-set:ISO-8859-1"作为contentType，导致中文有代码
    *   解决方案：给RequestMapping增加一个属性produces，使用这个属性指定新的contentType
    *
    * 返回对象框架的处理流程：
    *   1.框架会把返回String类型，调用框架中的ArrayList<HttpMessageConverter中每个类的canWriter()方法
    *       检查哪个HttpMessageConverter接口的实现类能处理String类型的数据--StringHttpMessageConverter
    *   2.框架会调用实现类的write(),StringHttpMessageConverter的write()方法
    *       把字符按照指定的编码处理 text/plain;char-set=ISO-8859-1
    *   3.框架会调用@ResponseBody把2的结果数据输出到浏览器，ajax请求处理完成
    * */
    @RequestMapping(value = "returnStringData.do",produces = "text/plain;char-set=utf-8")
    @ResponseBody
    public String doStringData(String name,Integer age){
        return "hello springmvc 返回对象，表示数据";
    }
}
