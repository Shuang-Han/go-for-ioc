package com.ganpengyu.study.ioc.dependency.lookup;

import com.ganpengyu.study.ioc.annotation.Super;
import com.ganpengyu.study.ioc.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookup {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        lookupInRealtime(beanFactory);
        lookupInLazy(beanFactory);

        lookupCollectionType(beanFactory);

        lookupByAnnotationType(beanFactory);
    }

    /**
     * 查找带有指定注解的 Bean
     * @param beanFactory
     */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println(map);
        }
    }

    /**
     * 查找集合类型的 bean
     * @param beanFactory
     */
    private static void lookupCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(map);
        }
    }

    /**
     * 通过 ObjectFactory 使用名称查找 bean
     * @param beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }

    /**
     * 使用名称+类型方式进行查找，避免了强制类型转换
     * @param beanFactory
     */
    private static void lookupInRealtime(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("实时查找：" + user);
    }


}
