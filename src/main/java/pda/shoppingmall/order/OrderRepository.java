package pda.shoppingmall.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class OrderRepository {

    private Map<Integer, Order> orderTable = new HashMap<>();
    private int id = 1;

    public void saveOrder(Order order){
        order.setId(id++);
        orderTable.put(order.getId(), order);
        log.info("order: {}", order);
    }

}
