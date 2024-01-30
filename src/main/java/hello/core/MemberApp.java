package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    // psvm
    // 순수 자바로만 실행되는 테스트 로직 => Junit으로 테스트(test 패키지에서 진행)
    public static void main(String[] args) {
/** 스프링으로 전환하기
        // appConfig에서 설정한(주입해서 만든) memberService 꺼내옴
        AppConfig appConfig = new AppConfig();
        // MemberService memberService = new MemberServiceImpl();
        MemberService memberService = appConfig.memberService();
*/
        //ApplicationContext : Spring 의 시작점! 스프링의 컨테이너!
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
                                                // 어노테이션 기반으로 Config하겠다. 파라미터에 설정파일.class 전달!
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
                                                // default: bean등록한 method명이 name으로 사용됨, 타입 class 전달



        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
