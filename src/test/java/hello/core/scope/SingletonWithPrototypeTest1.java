package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }

//    @Scope("singleton") // 생략가능, 디폴트가 싱글톤
    static class ClientBean{
        private final PrototypeBean prototypeBean; // 프로토타입 스코프 빈을 의존관계주입 받아서 사용할거임

        @Autowired // 생성자 하나일 땐 생략가능
        public ClientBean(PrototypeBean prototypeBean){
            /**
             * Singleton scope 빈이 등록되면서, DI가 일어나는데,
             * 이 떄 의존성주입을 위해 스프링컨테이너에 Prototype Scope 빈의 생성요청을 함
             * 싱글톤 빈이 이때 주입받은 PrototypeScope 빈을 계속 들고있음
             * => 이거는 Prototype Bean을 사용한 의도와 다르게 동작하는거다!!
             */
            this.prototypeBean = prototypeBean;
        }
        public int logic(){
            prototypeBean.addCount();
            int count = prototypeBean.getCount(); // Tips ctrl+alt+N : 인라인으로 합쳐줌
            return count;
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }
        
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean init" + this);
            
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean destroy" + this);
        }
    }
}
