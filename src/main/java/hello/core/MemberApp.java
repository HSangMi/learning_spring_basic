package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // psvm
    // 순수 자바로만 실행되는 테스트 로직 => Junit으로 테스트(test 패키지에서 진행)
    public static void main(String[] args) {
        // appConfig에서 설정한(주입해서 만든) memberService 꺼내옴
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
