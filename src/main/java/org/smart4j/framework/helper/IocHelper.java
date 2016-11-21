package org.smart4j.framework.helper;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 * @author zhangrh
 * @date 2016/11/21 0021
 */
public final class IocHelper {

    static {
        //获取所有的Bean类和Bean实例之间的映射关系(简称Bean Map)
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(MapUtils.isNotEmpty(beanMap)){
            //遍历Bean Map
            for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
                //从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean类定义的所有成员变量(简称 Bean Field)
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtils.isNotEmpty(beanFields)){
                    //遍历beanFields
                    for(Field beanField:beanFields){
                        //判断当前Bean Field 是否带有Inject注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            //在Bean Map 获取Bean Field对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                //通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }

                }
            }

        }

    }
}
