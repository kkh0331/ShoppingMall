package pda.shoppingmall.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.ToString;
import org.aspectj.weaver.ast.Or;
import pda.shoppingmall.order.Order;

@Getter
@ToString
public class OrderProductReqDTO {

    @JsonProperty("product_id")
    @Min(value = 1, message = "상품 아이디는 양수이어야 합니다.")
    private Long productId;

    @JsonProperty("order_count")
    @Min(value = 1, message = "주문 개수는 1이상이어야 합니다.")
    private Long count;

    public Order convertToEntity(){
        return new Order(productId, count);
    }

}
