package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * ��װAction��Ϣ
 * @author zhangrh
 * @date 2016/11/22 0022
 */
public class Handler {
    /**
     * Controller��
     */
    private Class<?> controllerClass;

    /**
     * Action ����
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

}
