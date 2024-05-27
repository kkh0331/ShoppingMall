package pda.shoppingmall.member.repository;

import pda.shoppingmall.member.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findByUserId(String userId);

}
