package top.callback.study.ioc.dependency.injection;

import top.callback.study.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjection {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        UserRepository userRepository = beanFactory.getBean(UserRepository.class);

        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        System.out.println(userRepository.getObjectFactory());
        System.out.println(userRepository.getObjectFactory().getObject() == beanFactory);

        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }
}
