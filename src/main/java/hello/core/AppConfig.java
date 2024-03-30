package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderserviceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. 어플리케이션의 전체 동작 방식을 구성(config)하기 위해, **구현 객체를 생성**하고 **연결**하는 책임을 가지는 별도의 설정클래스 만들기
 * 2. **역할**과 **구현**이 잘 드러나게 바꾸기!
 */

/**
 * ### 스프링으로 전환하기 ###
 * - @Configuration 어노테이션 추가
 *    - 어플리케이션 구성정보를 설정함을 의미
 * - 메소드에 @Bean 어노테이션 추가
 *    - Spring 컨테이너에 등록 됨
 */
@Configuration
public class AppConfig {
    /**
     * 2 AppConfig 리팩토링 전
     * - 역할이 명확하지 않고 중복된 코드들이 있음
     */
//    public MemberService memberService(){
//        // 생성자 주입
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderserviceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }
    /** FIXME 역할이 잘 드러나도록 변경! (단축키 ctrl + alt + M 2번 누르면 리턴타입 선택가능! 구체클래스x 인터페이스로 선택! )
     - 장점
        - 메소드 이름만 봐도 역할이 구분되고, 그 구현이 어떤건지 한눈에 들어옴 + 중복코드가 사라짐.
        - 이후 구현체가 바뀌었을 때, 이 곳에서만 관리해주면 된다.
     */

    /* MemberService 역할 */
    @Bean
    public MemberService memberService(){
        // 생성자 주입
//        return new MemberServiceImpl(new MemoryMemberRepository());
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    /* MemberRepository 역할 */
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    /* OrderService 역할 */
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderserviceImpl(memberRepository(), discountPolicy());
    }
    /* DicountPolicy 역할 */
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
