package pda.shoppingmall.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.product.Product;
import pda.shoppingmall.product.ProductService;
import pda.shoppingmall.utils.Validator;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private OrderService orderService;
    private ProductService productService;

//    @PostMapping("")
//    public void orderProduct(@RequestBody OrderDTO orderDto){
//        log.info("order : {}", orderDto);
//        if(Validator.isNumber(orderDto.getProductId())){
//            Product orderedProduct = productService.findProduct(orderDto.getProductId());
//
//            //TODO : 서비스로 이사 갈거에요... DTO -> Entity
//            Order requestOrder = new Order(orderedProduct, orderDto.getCount());
//
//
//            orderService.orderProduct(requestOrder);
//        }
//    }

}
