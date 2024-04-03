package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration  // 스프링 설정정보를 가진 빈, @Configuration도 자동 빈 등록 대상 ( 내부에 @Component이 있음)
@ComponentScan( // 컴포넌트 스캔
        // 컴포넌트 스캔 탐색 시작 위치 지정 (지정하지 않으면 전체 소스 스캔 (라이브러리 포함))
        basePackages = "hello.core",
//        basePackageClasses = AutoAppConfig.class
        // Configuration 어노테이션 제외 => AppConfig.java, 테스트용 설정 제외하려고!, 실무에서 제외하진않음!
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/**
 사용할 구현체에다가 @Component 추가하면, Bean 등록을 별도로 하지않아도 됨.
 MemoryMemberRepository, RateDiscountPolicy, MemberServiceImpl

 * @Component를 사용해 스프링 빈으로 자동으로 등록을 할 뿐, 의존관계 주입을 해주진 않음
    => @Autowired를 사용해 자동 주입!
 * @Autowired를 사용하면 스프링 컨텍스트에 등록된 빈 중  타입에 맞는 빈을 찾아와서 의존관계 자동주입

 */
public class AutoAppConfig {

}
