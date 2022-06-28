package com.atguigu.ioc.pojo;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author yjq
 * @create 2022-05-20-19:55
 */
public class Person {

    private Integer age;
    private String name;
    private Car car;


    private String email;

    public void personInit(){
        System.out.println("调用了自定义的初始化方法,并且对person对象的属性进行修改");
        age+=1;
        name+="?";
    }

    public void  personDestory(){
        System.out.println("调用了自定义的销毁方法"); return ;
    }

    public Person() {
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", car=" + car +
                ", email='" + email + '\'' +
                '}';
    }
}
