package com.zyd.myrpc.consumer;

import com.zyd.myrpc.pojo.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  自定义HttpClient
 */
public class HttpClient {

    /**
     * 自定义HttpClient Post方法
     */
    public String post(String hostName, int port, Invocation invocation){
        
        try{
            //1、进行连接
            URL url = new URL("http", hostName, port, "/client");
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //2、发送调用的信息
            OutputStream outputStream = urlConnection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();
            objectOutputStream.close();

            //3、将输入流转换成字符串，获取远程调用的结果
            InputStream inputStream = urlConnection.getInputStream();
            return IOUtils.toString(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
