package hello.core.member;

/**
 * MemberService의 구현체
 * (구현체가 한개만 있을 경우, Impl을 붙이는 것이 관례)
 */
public class MemberServiceImpl implements MemberService {

    // 인터페이스의 구현 객체를 선택해서 넣어줌
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        // 다형성에 의해, 구현체에서 Override한 함수가 실행됨
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
