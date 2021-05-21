import com.graduation.ssm.service.testImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {

    /**
     * 执行测试方法前执行
     */
    @Before
    public void before() {
        System.out.println("执行 before() 方法，开始测试；");
    }

    /**
     * 执行测试方法后执行
     */
    @After
    public void after() {
        System.out.println("执行 after() 方法，测试完毕；");
    }

    @Test
    public void testSayHi() {
        // 获取 Spring 容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");

        // 从 Spring 容器中获取对象
        testImpl testimpl = (testImpl) applicationContext.getBean("testImpl");
        testimpl.sayHi();
    }
}
