package pda.shoppingmall.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OrderDTO {

    @JsonProperty("order_id")
    private int productId;

    @JsonProperty("order_count")
    private int count;

}
