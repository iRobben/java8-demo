package org.smart4j.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangrh by 2016/12/4
 */
public class TestCollection {
    public static void main(String[] args) {
        People[] array = {new People("zhangsan",10),new People("wangwu",15),new People("lisi",25)};
        List<People> peopleList = Arrays.asList(array);
        peopleList.forEach(people ->{
            System.out.print(people.getName()+"-");
            System.out.println(people.getAge());
        });
        //map
        //Stream peopleStream = peopleList.stream().filter(people -> people.getAge()>10);
        long num = peopleList.stream().filter(people -> people.getAge()>10).count();
        System.out.println(num);
//        List<People> peopleList1 = peopleList.stream()
//                .filter(people -> people.getAge() >10)
//                .map(people ->people.returnPeople(people)).collect(Collectors.toList());
        List<People> peopleList1 = peopleList.stream()
                .filter(people -> people.getAge() >10)
                .map(people ->people.returnPeople(people)).collect(Collectors.toCollection(ArrayList::new));
        peopleList1.forEach(people ->{
            System.out.print(people.getName()+"-");
            System.out.println(people.getAge());
        });
    }

}
