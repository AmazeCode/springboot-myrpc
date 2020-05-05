package com.zyd.myrpc.tomcat;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * 自定义tomcat
 */
public class HttpServer {

    public void start(String hostName,int port){
        //实例一个tomcat
        Tomcat tomcat = new Tomcat();

        //构建server
        Server server = tomcat.getServer();

        //获取service
        Service service = server.findService("Tomcat");

        //构建Connector
        Connector connector = new Connector();
        connector.setPort(port);
        connector.setURIEncoding("UTF-8");

        //构建Engine(多态)
        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostName);

        //构建Host
        Host host = new StandardHost();
        host.setName(hostName);

        //构建Contest
        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());//声明周期监听器


        //然后按照server.xml,一层层把子节点添加到父节点
        host.addChild(context);
        engine.addChild(host);
        service.setContainer(engine);
        service.addConnector(connector);
        //service再getServer时就被添加到server节点了

        //tomcat是一个servlet，设置路径与映射
        tomcat.addServlet(contextPath,"dispatcher",new DisPatcherServlet());
        context.addServletMappingDecoded("/*","dispatcher");//设置的拦截路径为‘/*’表示将会拦截所有的请求

        try{
            tomcat.start();//启动tomcat
            tomcat.getServer().await(); //接受请求
        }catch (LifecycleException e){
            e.printStackTrace();
        }

    }
}
