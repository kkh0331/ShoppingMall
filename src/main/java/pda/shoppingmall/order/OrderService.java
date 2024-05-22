package pda.shoppingmall.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pda.shoppingmall.product.ProductMemoryRepository;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;
    private ProductMemoryRepository productRepository;

    public void orderProduct(Order order){
        log.info("order : {}", order);
        orderRepository.saveOrder(order);
    }

}
