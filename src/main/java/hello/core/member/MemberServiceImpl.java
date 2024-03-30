package hello.core.member;

/**
 * MemberService의 구현체
 * (구현체가 한개만 있을 경우, Impl을 붙이는 것이 관례)
 */
public class MemberServiceImpl implements MemberService {

    // 인터페이스의 구현 객체를 선택해서 넣어줌
    // => 생성자에서 주입하도록 변경. 인테페이스에만 의존하게 되고, 구현체코드
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        // 다형성에 의해, 구현체에서 Override한 함수가 실행됨
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용(싱글톤 보장 확인용)
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
