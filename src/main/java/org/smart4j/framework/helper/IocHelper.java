package org.smart4j.framework.helper;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * ����ע��������
 * @author zhangrh
 * @date 2016/11/21 0021
 */
public final class IocHelper {

    static {
        //��ȡ���е�Bean���Beanʵ��֮���ӳ���ϵ(���Bean Map)
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(MapUtils.isNotEmpty(beanMap)){
            //����Bean Map
            for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
                //��BeanMap�л�ȡBean����Beanʵ��
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //��ȡBean�ඨ������г�Ա����(��� Bean Field)
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtils.isNotEmpty(beanFields)){
                    //����beanFields
                    for(Field beanField:beanFields){
                        //�жϵ�ǰBean Field �Ƿ����Injectע��
                        if(beanField.isAnnotationPresent(Inject.class)){
                            //��Bean Map ��ȡBean Field��Ӧ��ʵ��
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                //ͨ�������ʼ��BeanField��ֵ
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }

                }
            }

        }

    }
}
