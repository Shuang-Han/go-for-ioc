package top.callback.study.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import top.callback.study.ioc.domain.User;

import java.util.Map;

@Configuration
public class BeanCreateDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanCreateDemo.class);

        // 使用 BeanDefinitionBuilder 构造器模式创建 BeanDefinition
        BeanDefinition bean1 = createByBuilder();

        // 通过 AbstractBeanDefinition 极其派生类创建 BeanDefinition
        BeanDefinition bean2 = createByAbstractBeanDefinition();

        // 注册 BeanDefinition
        registryBean(applicationContext, "user1", bean1);
        registryBean(applicationContext, "user2", bean2);

        applicationContext.refresh();

        Map<String, User> users = applicationContext.getBeansOfType(User.class);
        System.out.println(users);
    }

    private static void registryBean(BeanDefinitionRegistry registry, String beanName, BeanDefinition beanDefinition) {
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private static BeanDefinition createByBuilder() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 6L)
                .addPropertyValue("name", "子都");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        return beanDefinition;
    }

    private static BeanDefinition createByAbstractBeanDefinition() {
        AbstractBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", 5L)
                .add("name", "无名");
        genericBeanDefinition.setPropertyValues(propertyValues);
        return genericBeanDefinition;
    }

}
