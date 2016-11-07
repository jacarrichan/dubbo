package com.alibaba.dubbo.registry.common;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.resource.Resource;

import java.io.File;

public class XmlJettyServer {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/myapp");
        //----------------------------------------
        String war=context.getWar();
        String path=Resource.newSystemResource(".").getFile().getParentFile().getParent();
        path=path+"/"+war;
        //手动设置BaseResource是为了解决项目在idea中不是顶级项目导致的问题
        context.setBaseResource(Resource.newResource((new File(path)).toURI().toURL()));
        //----------------------------------------

        //解决请求的操作无法在使用用户映射区域打开的文件上执行。
       // context.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer","false");
        // ====================
        server.setHandler(context);

        server.start();
        server.join();
    }
}