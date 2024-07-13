package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // 1. ApplicationContext에 Config 파일을 읽어와 Bean으로 등록하고 (close 사용하기 위해 하위의 ConfigurableApplicationContext 사용)
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        // 2. ApplicationContext에서 Bean 꺼내오기
        NetworkClient client = ac.getBean(NetworkClient.class);
        // 3. ApplicationContext 닫기
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean //(initMethod = "init", destroyMethod = "close")
        // 호출로 반환된 결과물이 bean으로 등록, bean이름은 networkClient
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }
}
