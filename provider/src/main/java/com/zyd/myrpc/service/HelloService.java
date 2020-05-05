package com.zyd.myrpc.service;

/**
 * 服务端(手写rpc)步骤：
 * 1、定义服务接口和实现类
 * 2、实现HelloService
 * 3、服务注册：注册中心
 * （注册中心我们将服务注册再map集合，结构Map<String,Map<URL,Class>>外边map的key存储服务接口的全类名，URL封装了调用服务的ip和port,里面的value指定具体的实现类，注册中心提供注册服务并暴露服务和发现功能）
 */
public interface HelloService {

    public String sayHello(String name);
}
