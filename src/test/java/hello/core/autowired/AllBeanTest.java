package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * 모든 빈을 사용하고 싶은 경우,
 * 다형성을 활용해 유연한 전략패턴을 적용할 수 있다
 */
public class AllBeanTest {

    @Test
    void findAllBean(){
        // DiscountPolicy는 AutoAppConfig 설정에서 에서 가져와야징
        // 테스트하려는 서비스 - DiscountService.class를 넘겨 빈으로 등록할 수 있음
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // TDD로 비지니스 로직을 한번 만들어보겠습니다
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(2000);

    }

    // 테스트를 위한 DiscountService 생성(기존 OrderService손대긴 복잡)
    @Component
    static class DiscountService{
        private Map<String, DiscountPolicy> policyMap;
        private List<DiscountPolicy> policyList;        // 리스트도 가능~!

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
