package org.smart4j.framework.bean;

/**
 * 返回数据对象
 * @author zhangrh
 * @date 2016/11/22 0022
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model){
        this.model = model;
    }

    public Object getModel(){
        return model;
    }
}
