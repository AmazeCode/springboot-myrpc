package com.zyd.myrpc.service.impl;

import com.zyd.myrpc.service.HelloService;

/**
 * 实现类
 */
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return name+"调用了MyRPC的服务";
    }
}
