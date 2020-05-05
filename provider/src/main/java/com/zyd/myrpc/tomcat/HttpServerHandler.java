package com.zyd.myrpc.tomcat;

import com.zyd.myrpc.pojo.Invocation;
import com.zyd.myrpc.pojo.URL;
import com.zyd.myrpc.registry.NativeRegistry;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 *  处理远程调用请求
 */
public class HttpServerHandler {

    /**
     * 服务处理
     */
    public void handle(HttpServletRequest req, HttpServletResponse res){

        try{
            //服务请求的处理逻辑

            //1、通过请求流获取请求服务调用的参数
            InputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Invocation invocation = (Invocation) objectInputStream.readObject();

            //从注册中心获取服务的列表
            Class implClass = NativeRegistry.get(invocation.getInterfaceName(),new URL("localhost",8080));

            //调用服务 反射
            Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParamTypes());

            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

            //4、结果返回
            IOUtils.write(result,res.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
