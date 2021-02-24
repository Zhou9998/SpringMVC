ch04-return：处理器方法的返回值表示请求的处理结果
    1.ModelAndView：有数据和视图，对视图执行forward
    2.String：表示视图，可以是逻辑名称，也可以时完整视图路径
    3.void：不能表示数据，也不能表示视图
            在处理ajax的时候，可以使用void返回值。通过HttpServletResponse输出数据，响应ajax请求。
            ajax请求服务器返回的就是数据，和视图无关。
    4.object：例如String，Integer，Map，List，Student等等都是对象，对象有属性，属性就是数据。所以返回Object表示数据，和视图无关。
            可以使用对象表示的数据响应ajax请求。
            现在做ajax，主要是用json的数据格式。实现步骤：
                1.加入处理json的工具库的依赖，springmvc默认使用的是Jackson
                2.在springmvc配置文件中加入<mvc:annotation-driven>注解驱动
                    json = om.writeValueAsString(student);
                3.在处理器方法的上面加入@ResponseBody注解
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter pw = response.getWriter();
                    pw.println(json);

            springmvc处理器方法返回object，可以转为json输出到浏览器，响应ajax的内部原理
                1.<mvc:annotation-driven>注解驱动。
                    注解驱动实现的功能是完成java对象到json，xml，text，二进制等数据格式的转换。
                    <mvc:annotation-driven>在加入到springmvc配置文件后，会自动创建HttpServletConverter接口的7个实现类对象，
                    包括MappingJackson2HttpMessageConverter(使用jackson工具库中的ObjectMapper实现java对象转为json字符串)

                    HttpMessageConverter接口：消息转换器。
                    功能：定义了java转为json，xml等数据格式的方法。这个接口有很多的实现类。
                        这些实现类完成java到json，java对象到xml，java对象到二进制数据的转换。

                    下面的两个方法是控制器类把结果输出给浏览器时使用的：
                    boolean canWrite(Class<?> var1, @Nullable MediaType var2);
                    void write(T var1, @Nullable MediaType var2, HttpOutputMessage var3);

                        例如处理器方法
                        @RequestMapping(value = "/returnString.do")
                        public Student doReturnView2(HttpServletRequest request,String name,Integer age){
                            Student student = new Student();
                            student.setName("lisi");
                            student.setAge(20);
                            return student;
                        }
                        1)canWrite作用检查处理器方法的返回值，能不能转为var2表示的数据格式。
                            检查student("lisi",20)能不能转为var2表示的数据结构。如果检查能转为json，canWrite返回true
                            MediaType:表示格式的，例如json，xml等等
                        2)write：把处理器方法的返回值对象，调用jackson中的ObjectMapper转为json字符串。
                            json = om.writeValueAsString(student);


                2.@ResponseBody注解：
                    放在处理器方法的上面，通过HttpServletResponse输出数据，相应ajax请求的。
                        PrintWriter pw = response.getWriter();
                        pw.println(json);
                        pw.flush();
                        pw.close();
注意：
    在提交请求参数时，get请求方式中文没有乱码
    使用post方式提交请求时，中文有乱码，需要使用过滤器处理乱码的问题

过滤器可以自定义，也可使用框架中提供的过滤器 CharacterEncodingFilter
实现步骤：
    1.web maven工程
    2.加入依赖
        spring-webmvc依赖，间接的把spring的依赖都加入到项目之中
        jsp、servlet依赖
    3.重点：在web.xml中注册springmvc框架的和新对象DispatcherServlet
        1）DispatcherServlet叫做中央调度器，是一个servlet，他的父类是集成HttpServlet
        2）DispatcherServlet也叫做前端控制器（front controller）
        3）DispatcherServlet负责接收用户提交的请求，调用其他的控制器对象，并把请求的处理结果显示给用户
    4.创建一个发起请求的页面 index.jsp
    5.创建控制器类
        1）在类的上面加入@Controller注解，创建对象，并放入到springmvc容器中
        2）在类中的方法上面，加入@RequestMapping注解。
    6.创建一个作为结果的jsp，显示请求的处理结果。
    7.创建springmvc的配置文件（spring的配置文件一样）
        1）声明组件扫描器，指定@COntroller注解所在的包名
        2）声明视图解析器，帮助处理视图
