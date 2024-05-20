package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        // TestBean이 스프링 빈으로 등록됨.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
    // 임의의 테스트 빈
    static class TestBean{
        // 스프링 빈이 아닌 Member로 테스트 => 자동으로 등록하지 않는 걸로 테스트하려고!

        @Autowired(required = false)
        public void setNoBean1(Member mem){
            // 아예 실행이 안되므로 출력 안됨!
            System.out.println("mem = " + mem);
        }

        @Autowired
        public void setNoBean2(@Nullable Member mem){
            System.out.println("mem = " + mem);
        }

        @Autowired
        public void setNoBean3(Optional<Member> mem){
            System.out.println("mem = " + mem);

        }

        
    }
}
