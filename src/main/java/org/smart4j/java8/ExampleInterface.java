package org.smart4j.java8;

/**
 * @author zhangrh by 2016/12/2
 */
public interface ExampleInterface {

    default void print(){
        System.out.println("默认方法执行");
    }
}
