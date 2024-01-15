package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderserviceImpl();

    // AppCnofig에서 가져오도록 변경해보자!
    // 1. 선언
    MemberService memberService;
    OrderService orderService;
    // 2. 각 테스트가 실행 되기전 수행할 메소드 추가
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);


        Order itemA = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(itemA.getDiscountPrice()).isEqualTo(1000);

    }

}
