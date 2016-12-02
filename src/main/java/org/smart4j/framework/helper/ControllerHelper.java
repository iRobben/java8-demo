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
     * 处理请求与处理器之间的映射关系
     */
    private static final Map<Request,Handler> REQUEST_HANDlER_MAP = new HashMap<>();

    static {
        //获取所有的controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)){
            //遍历
            for(Class<?> controllerClass:controllerClassSet){
                //获取controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtils.isNotEmpty(methods)){
                    //遍历
                    for(Method method:methods){
                        //是否有action注解
                        if(method.isAnnotationPresent(Action.class)){
                            //从action中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证URL规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtils.isNotEmpty(array) && array.length == 2){
                                    //获取请求方法和请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //放入action Map中
                                    REQUEST_HANDlER_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     */
    public static Handler getHandler(String requestMehtod,String requestPath){
        Request request = new Request(requestMehtod,requestPath);
        return REQUEST_HANDlER_MAP.get(request);
    }
}
