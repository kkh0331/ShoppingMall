package pda.shoppingmall.order.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import pda.shoppingmall.order.Order;

@Primary
public interface OrderJPARepository extends JpaRepository<Order, Long>, OrderRepository{

}
