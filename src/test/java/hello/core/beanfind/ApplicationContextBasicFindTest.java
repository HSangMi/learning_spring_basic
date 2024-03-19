package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        // 검증
        //assertj의 Assertions 사용
//        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  // alrt + Enter 에 Add on-demand 옵션으로 축약
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입으로만 빈 조회")
    void findBeanByType(){
        // 인터페이스로 조회하면 구현체가 대상으로 반환됨
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 빈 조회")
    void findBeanByName2(){
        // 이상적으로 조회하는 방법은 아님! 역할에 의존하지 않고 구현에 의존된 코드기 때문 => 유연성 떨어짐
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
//        MemberService memberService = ac.getBean("xxxx", MemberService.class);
        // NoSuchBeanDefinitionException 발생!!
        // 에러나는 것을 검증해보기
        // Junit의 Assertions.assserThrows() 사용!!
        // NoSuchBeanDefinitionException.class에러가 터져야 성공인 테스트
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            ac.getBean("xxxx", MemberService.class);
        });
    }
}
