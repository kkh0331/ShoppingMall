package pda.shoppingmall.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import pda.shoppingmall.product.Product;

@Getter
@ToString
public class OrderProductResDTO {

    private Long id;

    private Product pruduct; // Domain-bjec

    @JsonProperty("order_count")
    private Long count;

    public OrderProductResDTO(Long id, Product orderedProduct, Long count) {
        this.id = id;
        this.pruduct = orderedProduct;
        this.count = count;
    }

}
