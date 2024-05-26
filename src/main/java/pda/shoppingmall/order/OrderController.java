package pda.shoppingmall.order;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.order.dto.OrderProductReqDTO;
import pda.shoppingmall.order.dto.OrderProductResDTO;
import pda.shoppingmall.product.Product;
import pda.shoppingmall.product.ProductService;
import pda.shoppingmall.utils.ApiUtils;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private OrderService orderService;
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity orderProduct(@Valid @RequestBody OrderProductReqDTO orderProductReqDTO){
        log.info("OrderProductReqDTO : {}", orderProductReqDTO);

        Product orderedProduct = productService.findProduct(orderProductReqDTO.getProductId());

        OrderProductResDTO orderProductResDTO = orderService.orderProduct(orderProductReqDTO, orderedProduct);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtils.success(orderProductResDTO));
    }

}
