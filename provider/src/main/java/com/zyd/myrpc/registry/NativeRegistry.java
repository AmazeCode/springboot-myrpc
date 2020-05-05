package com.zyd.myrpc.registry;

import com.zyd.myrpc.pojo.URL;

import java.util.HashMap;
import java.util.Map;

/**
 *  注册中心
 */
public class NativeRegistry {

    //构建注册中心
    private static Map<String, Map<URL,Class>> registryCenter = new HashMap<String, Map<URL,Class>>();

    /**
     * 注册服务
     */
    public static void registry(String interfaceName,URL url,Class implClass){
        Map<URL,Class> map = new HashMap<URL,Class>();
        map.put(url,implClass);
        //向注册中心注册
        registryCenter.put(interfaceName,map);
    }

    /**
     * 获取服务信息
     */
    public static Class get(String interfaceName,URL url){
        //调用map的构造方法
        Map<URL,Class> urlClassMap = registryCenter.get(interfaceName);
        Class aClass = urlClassMap.get(url);
        return aClass;
    }
}
