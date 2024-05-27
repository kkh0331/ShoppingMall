package pda.shoppingmall.member.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import pda.shoppingmall.member.Member;

@Primary
public interface MemberJPARepository extends JpaRepository<Member, Long>, MemberRepository {

}
