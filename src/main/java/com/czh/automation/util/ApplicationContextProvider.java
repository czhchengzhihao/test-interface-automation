package com.czh.automation.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.anheng.daslink.util
 * @ClassName: ApplicationContextProvider
 * @Description: ApplicationContextProvider/description:TODO 解决TestNG的dataProviderClass不能加载注入的bean
 * @Author: ChengZhiHao
 * @Date: 2023/2/21 18:25
 * @Version: v1.0
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

/*问题描述：
    项目中用到了定时任务，单独建了定时任务的包并将定时任务的具体业务逻辑写在了定时任务的包下，由于需要到数据库中查询设置的定时任务执行间隔以及需要和数据库进行交互，
    就通过@Autowired注解注入了service，运行后发现注入的servicer为null。包括在test包下写测试的时候如果单纯通过@Autowired对service层进行注入同样会报null。*/
/*解决：
    以实现ApplicationContextAware接口的方式获取ApplicationContext对象采用ApplicationContextAware的方式完成ApplicationContext实例的获取，
    并通过ApplicationContext实例完成对Spring管理的Bean实例手动获取。
    之后如果再需要注入service则需要通过
    class方式：InjuryService injuryService = ApplicationContextProvider.getBean(InjuryService.class);
    name方式：在对应的实现类中根据@Service注释后面价格对应的name名称；如@Service("responseResultService")然后在调用
    ResponseResultService responseResultService = (ResponseResultService) ApplicationContextProvider.getBean("responseResultService");
*/
/*分析原因：
    可能是由于我们在非Spring管理的类或实体中采用注入的方式获取容器管理的类或实体。这里要注意ApplicationContextProvider类上的@Component注解是不可以去掉的，
    去掉后Spring就不会自动调用setApplicationContext方法来为我们设置上下文实例。*/


}


