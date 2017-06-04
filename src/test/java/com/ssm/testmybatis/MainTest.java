package com.ssm.testmybatis;

import com.render.PasswordUtils;
import com.render.annotation.UseCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yg on 2017/3/6.
 */
public class MainTest {

    /**ClassPathXMLApplicationContext
     * @param args
     */
    public static void main(String[] args) {
//        // spring - schedule
//        System.out.println("Test start.");
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-job.xml");
//        //如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
//        context.getBean("startQuertz");
//        System.out.print("Test end..");
//
//
        // 线程池实例
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("spring-thread-pool.xml");
        ThreadPoolTaskExecutor poolTaskExecutor = (ThreadPoolTaskExecutor)
                ctx.getBean("taskExecutor");
//
//        poolTaskExecutor.execute(new PrintThread());
//        // 获取当前线程池活动的线程数：
//        int count = poolTaskExecutor.getActiveCount();
//        System.out.print("当前线程池活动的线程数：" + count + "----\n");


        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }

    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {


//        cl.getDeclaredField(); // 获取方法
        for (Method m : cl.getDeclaredMethods()) {

            UseCase uc = m.getAnnotation(UseCase.class);
            System.out.print("\n 2uc is" + uc);
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " "
                        + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (int i : useCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }
}
