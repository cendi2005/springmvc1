package dd.spring.Interceptor;

import java.util.Locale;
public class RequestDetail {
    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    public String getRequestedSessionId() {
        return requestedSessionId;
    }

    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServletPath() {
        return servletPath;
    }

    public void setServletPath(String servletPath) {
        this.servletPath = servletPath;
    }

    //获取本地IP，即服务器IP
    private String localAddr;
    //获取本地名称，即服务器名称
    private String localName;

    //获取本地端口号，即Tomcat端口号
    private int localPort;
    //用户的语言环境
    private Locale locale;
    //context路径
    private String contextPath;
    //GET还是POST
    private String method;
    //协议，http协议
    private String protocol;
    //查询字符串
    private String queryString;
    //远程IP，即客户端IP
    private String remoteAddr;
    //远程端口，即客户端端口
    private int remotePort;
    //远程用户
    private String remoteUser;
    //客户端的Session的ID
    private String requestedSessionId;
    //用户请求的URL
    private String requestURI;
    //协议头，例如http
    private String scheme;
    //服务器名称
    private String serverName;
    //Servlet路径
    private String servletPath;

    @Override
    public String toString() {
        return "RequestDetail{" +
                "localAddr='" + localAddr + '\'' +"\r\n"+
                ", localName='" + localName + '\'' +"\r\n"+
                ", localPort=" + localPort +"\r\n"+
                ", locale=" + locale +"\r\n"+
                ", contextPath='" + contextPath + '\'' +"\r\n"+
                ", method='" + method + '\'' +"\r\n"+
                ", protocol='" + protocol + '\'' +"\r\n"+
                ", queryString='" + queryString + '\'' +"\r\n"+
                ", remoteAddr='" + remoteAddr + '\'' +"\r\n"+
                ", remotePort=" + remotePort +"\r\n"+
                ", remoteUser='" + remoteUser + '\'' +"\r\n"+
                ", requestedSessionId='" + requestedSessionId + '\'' +"\r\n"+
                ", requestURI='" + requestURI + '\'' +"\r\n"+
                ", scheme='" + scheme + '\'' +"\r\n"+"\r\n"+
                ", serverName='" + serverName + '\'' +"\r\n"+
                ", servletPath='" + servletPath + '\'' +"\r\n"+
                '}';
    }
}
