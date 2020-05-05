package com.zyd.myrpc.tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  过滤器
 */
public class DisPatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HttpServerHandler正真处理远程调用请求
        new HttpServerHandler().handle(req,resp);
    }
}
