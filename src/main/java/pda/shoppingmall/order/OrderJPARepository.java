package pda.shoppingmall.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pda.shoppingmall.member.Member;

import java.util.Optional;

@Repository
public interface OrderJPARepository extends JpaRepository<Order, Long>{

}
