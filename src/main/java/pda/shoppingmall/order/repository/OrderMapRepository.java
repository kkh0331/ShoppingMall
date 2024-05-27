package pda.shoppingmall.order.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pda.shoppingmall.order.Order;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class OrderMapRepository implements OrderRepository{

    private Map<Long, Order> orderTable = new HashMap<>();
    private Long id = 1L;

    @Override
    public Order save(Order order) {
        order.setId(id++);
        orderTable.put(order.getId(), order);
        return orderTable.get(order.getId());
    }
}
