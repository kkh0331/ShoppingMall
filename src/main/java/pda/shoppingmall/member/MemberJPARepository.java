package pda.shoppingmall.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberJPARepository extends JpaRepository<Member, Long>{

    Optional<Member> findByUserId(String userId);

}
