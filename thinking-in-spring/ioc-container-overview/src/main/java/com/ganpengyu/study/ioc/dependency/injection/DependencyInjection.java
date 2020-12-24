package com.ganpengyu.study.ioc.dependency.injection;

import com.ganpengyu.study.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjection {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println(userRepository.getUsers());
    }
}
