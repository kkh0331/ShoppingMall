package pda.shoppingmall.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pda.shoppingmall.order.dto.OrderProductResDTO;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class OrderRepository {

    private Map<Integer, OrderProductResDTO> orderTable = new HashMap<>();
//    private int id = 1;
//
//    public void saveOrder(OrderResDTO orderResDTO){
////        orderResDTO.setId(id++);
//        orderTable.put(orderResDTO.getId(), orderResDTO);
//        log.info("order: {}", orderResDTO);
//    }

}
