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
 * ������������
 * @author zhangrh
 * @date 2016/11/22 0022
 */
public class ControllerHelper {

    /**
     * ���ڴ�������봦������ӳ���ϵ
     */
    private static final Map<Request,Handler> REQUEST_HANDER_MAP = new HashMap<>();

    static {
        //��ȡ���е�controller��
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)){
            //����controller��
            for(Class<?> controllerClass:controllerClassSet){
                //��ȡcontroller���ж���ķ���
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtils.isNotEmpty(methods)){
                    //���������еķ���
                    for(Method method:methods){
                        //�жϵ�ǰ�����Ƿ����Actionע��
                        if(method.isAnnotationPresent(Action.class)){
                            //��Actionע���л�ȡURLӳ�����
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //��֤URLӳ�����
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtils.isNotEmpty(array) && array.length == 2){
                                    //��ȡ���󷽷�������·��
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //��ʼ��REQUEST_HANDER_MAP
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
     * ��ȡ handler
     */
    public static Handler getHandler(String requestMehtod,String requestPath){
        Request request = new Request(requestMehtod,requestPath);
        return REQUEST_HANDER_MAP.get(request);
    }
}
