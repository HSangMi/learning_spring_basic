package hello.core.member;

import java.util.HashMap;
import java.util.Map;

/**
 * MemberRepository 구현체, 메모리를 저장소로 사용
 * (인터페이스와 다른 패키지에 두는 것이 좋으나, 간단한 예제이므로 생략)
 */
public class MemoryMemberRepository implements MemberRepository{

    // 메모리 저장소로 쓸 Map, 동시성 이슈로 ConcurrentHashMap을 쓰는게 좋지만, 여기선 생략
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
        // 오류처리는 생략
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
