ch03-recevieparam:接受请求的参数
接受请求参数，使用的处理器方法的形参
    1)HttpServletRequest
    2)HttpServletResponse
    3)HttpSession
    4)用户提交的数据
接受用户提交的参数：
    1)逐个接受
    2)对象接受


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
