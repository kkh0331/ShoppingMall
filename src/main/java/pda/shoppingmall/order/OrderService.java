package pda.shoppingmall.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pda.shoppingmall.order.dto.OrderProductReqDTO;
import pda.shoppingmall.order.dto.OrderProductResDTO;
import pda.shoppingmall.product.Product;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private pda.shoppingmall.order.repository.OrderRepository OrderRepository;

    public OrderProductResDTO orderProduct(OrderProductReqDTO orderProductReqDTO, Product orderedProduct){
        Order reqOrder = orderProductReqDTO.convertToEntity();
        Order savedOrder = OrderRepository.save(reqOrder);
        return savedOrder.convertToOrderProductResDTO(orderedProduct);
    }

}
