package com.zyd.myrpc.pojo;
/**
 *  实体用于封装ip和端口
 */
public class URL {

    private String hostName;

    private Integer port;

    /**
     * 构造方法
     */
    public URL(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    
    @Override
    public int hashCode() {
        return hostName.hashCode();
    }

    /**
     * 重写equals方法原因：不是去比较地址值，而是要去比较变量的内容（hostname和ports）
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if (!(obj instanceof URL)){
            return false;
        }
        URL url = (URL) obj;
        //hostName不为空，por相等
        if(hostName.equals(((URL) obj).getHostName()) && port.intValue() == url.port.intValue()){
            return true;
        }
        return super.equals(obj);
    }
}
