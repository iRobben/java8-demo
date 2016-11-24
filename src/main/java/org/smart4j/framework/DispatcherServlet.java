package org.smart4j.framework;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器
 * @author zhangrh
 * @date 2016/11/22 0022
 */
@WebServlet(urlPatterns = "/",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化相关的Helper类
        HelperLoader.init();
        //获取ServletContext对象（用于注册Servlet）
        ServletContext servletContext = config.getServletContext();
        //注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法与请求路径
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        //获取Action 处理器
        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
        if(handler != null){
            //获取Controller类及其Bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //请求参数对象
            Map<String,Object> paramMap = new HashMap<>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body = CodeUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if(StringUtil.isNotEmpty(body)){
                String[] params = body.split("&");
                if(ArrayUtils.isNotEmpty(params)){
                    for(String param:params){
                        String[] array = param.split("=");
                        if(ArrayUtils.isNotEmpty(array) && array.length==2){
                            paramMap.put(array[0],array[1]);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            //调用Action
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
            //处理Action方法返回值
            if(result instanceof View){
                //返回JSP页面
                View view = (View)result;
                String path = view.getPath();
                if(StringUtil.isNotEmpty(path)){
                    if(path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath()+path);
                    }else {
                        Map<String,Object> model = view.getModel();
                        for(Map.Entry<String,Object> entry:model.entrySet()){
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);
                    }
                }
            }else if(result instanceof Data){
                //返回JSON数据
                Data data = (Data)result;
                Object model = data.getModel();
                if(model != null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }

    }


}
