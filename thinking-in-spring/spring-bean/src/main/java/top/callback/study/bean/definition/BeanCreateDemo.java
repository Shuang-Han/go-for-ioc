package top.callback.study.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import top.callback.study.ioc.domain.User;

public class BeanCreateDemo {

    public static void main(String[] args) {
        // 使用 BeanDefinitionBuilder 构造器模式创建 Bean
        BeanDefinition bean1 = createByBuilder();

        // 通过 AbstractBeanDefinition 极其派生类创建 Bean
        BeanDefinition bean2 = createByAbstractBeanDefinition();
    }

    private static BeanDefinition createByAbstractBeanDefinition() {
        AbstractBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", "5L")
                .add("name", "青青草原");
        genericBeanDefinition.setPropertyValues(propertyValues);
        return genericBeanDefinition;
    }

    private static BeanDefinition createByBuilder() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", "5L")
                .addPropertyValue("name", "青青草原");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        return beanDefinition;
    }

}
