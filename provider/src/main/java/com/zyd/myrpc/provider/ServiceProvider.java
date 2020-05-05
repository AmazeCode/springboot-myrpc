package com.zyd.myrpc.provider;

import com.zyd.myrpc.pojo.URL;
import com.zyd.myrpc.registry.NativeRegistry;
import com.zyd.myrpc.service.HelloService;
import com.zyd.myrpc.service.impl.HelloServiceImpl;
import com.zyd.myrpc.tomcat.HttpServer;

/**
 *  注册服务
 */
public class ServiceProvider {

    public static void main(String[] args) {

        //真正的注册服务
        URL url = new URL("localhost", 8080);
        NativeRegistry.registry(HelloService.class.getName(),url, HelloServiceImpl.class);


        //启动tomcat,暴漏服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(),url.getPort());
    }
}
