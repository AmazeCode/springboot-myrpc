package com.zyd.myrpc.consumer;

import com.zyd.myrpc.pojo.Invocation;
import com.zyd.myrpc.service.HelloService;

public class ConsumerMain {

    public static void main(String[] args) {

        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Object[]{"myRPC的客户端"}, new Class[]{String.class});

        HttpClient httpClient = new HttpClient();
        //调用post方法
        String result = httpClient.post("localhost",8080,invocation);
        System.out.println(result);
    }
}
