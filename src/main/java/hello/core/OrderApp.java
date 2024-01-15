package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderserviceImpl;

public class OrderApp {

    // order 서비스가 제대로 동작하는지 확인해보자! (Junit 익숙해지기 전)
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderserviceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order itemA = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("itemA = " + itemA);
        System.out.println("itemA.calculatePrice = " + itemA.calculatePrice());

    }
}
