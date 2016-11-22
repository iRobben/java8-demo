package org.smart4j.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制类助手类
 * @author zhangrh
 * @date 2016/11/22 0022
 */
public class ControllerHelper {

    /**
     * 用于存放请求与处理器的映射关系
     */
    private static final Map<Request,Handler> REQUEST_HANDER_MAP = new HashMap<>();

    static {
        //获取所有的controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)){
            //遍历controller类
            for(Class<?> controllerClass:controllerClassSet){
                //获取controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtils.isNotEmpty(methods)){
                    //遍历该类中的方法
                    for(Method method:methods){
                        //判断当前方法是否带有Action注解
                        if(method.isAnnotationPresent(Action.class)){
                            //从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证URL映射规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtils.isNotEmpty(array) && array.length == 2){
                                    //获取请求方法和请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //初始化REQUEST_HANDER_MAP
                                    REQUEST_HANDER_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 handler
     */
    public static Handler getHandler(String requestMehtod,String requestPath){
        Request request = new Request(requestMehtod,requestPath);
        return REQUEST_HANDER_MAP.get(request);
    }
}
