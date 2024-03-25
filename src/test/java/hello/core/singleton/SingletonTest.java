package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없느 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1.조회 : 호출할 때마다 객체를 생성
        MemberRepository memberRepository1 = appConfig.memberRepository();
        MemberRepository memberRepository2 = appConfig.memberRepository();

        // 참조값이 다른 것을 확인
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        // memberService1 != memberService2
        assertThat(memberRepository1).isNotSameAs(memberRepository2);

        /**
         * 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청 할 때 마다 객체를 새로 생성
         * => 메모리 낭비
         * => 해결 방안 : 객체를 1개만 생성하고, 공유하도록 설계 => 싱글톤 패턴!
         */
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
//        new SingletonTest();   // 컴파일 오류!!

        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance2);
        // same : == 비교
        // equal : equals 메소드 비교 (오버라이딩 할 수 있음)
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1.조회 : 호출할 때마다 객체를 생성
//        MemberRepository memberRepository1 = appConfig.memberRepository();
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
