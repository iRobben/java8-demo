package org.smart4j.java8;

/**
 * @author zhangrh by 2016/12/4
 */
public class People {
    private String name;
    private int age;

    public People(String name,int age){
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public People returnPeople(People people){
        people.setAge(people.getAge()+10);
        people.setName(people.getName()+"_new");
        return people;
    }
}
