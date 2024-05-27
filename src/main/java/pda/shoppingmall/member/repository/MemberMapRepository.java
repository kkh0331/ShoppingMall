package pda.shoppingmall.member.repository;

import lombok.extern.slf4j.Slf4j;
import pda.shoppingmall.member.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class MemberMapRepository implements MemberRepository {

    private Map<String, Member> memberTable = new HashMap<>();
    private Long id = 1L;

    @Override
    public Member save(Member member) {
        member.setId(id++);
        memberTable.put(member.getUserId(), member);
        return memberTable.get(member.getUserId());
    }

    @Override
    public Optional<Member> findByUserId(String userId){
        return Optional.ofNullable(memberTable.get(userId));
    }

}
