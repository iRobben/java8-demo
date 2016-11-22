package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 * @author zhangrh
 * @date 2016/11/22 0022
 */
public final class HelperLoader {

    public static void init(){
      Class<?>[] classArray = {
              ClassHelper.class,
              BeanHelper.class,
              IocHelper.class,
              ControllerHelper.class
      } ;
      for (Class<?> cls:classArray){
          ClassUtil.loadClass(cls.getName(),false);
      }
    }
}
