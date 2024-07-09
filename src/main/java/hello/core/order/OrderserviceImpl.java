package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderserviceImpl implements OrderService{

    //    추상클래스(인터페이스)에만 의존하도록 변경
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // final 선언되어있으면 기본할당이든 생성자인든 할당이되어야함!

    // Autowired 주입 시, 빈이 2개 이상인 경우, 필드이름으로 매칭 (rateDiscountPolicy)
    // (참고, appConfig.xml에 discountPolicy이름으로 bean이 등록되어있어서 전체테스트코드는 실패)
    @Autowired
    public OrderserviceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원정보 조회 후
    Member member = memberRepository.findById(memberId);
        // 2. 할인가격 할인정책에 넘김
    int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

    public DiscountPolicy getDiscountPolicy(){ return discountPolicy; }
}
