package pda.shoppingmall.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pda.shoppingmall.order.dto.OrderProductResDTO;
import pda.shoppingmall.product.Product;

@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long orderCount;

    public Order(Long productId, Long orderCount) {
        this.productId = productId;
        this.orderCount = orderCount;
    }

    public OrderProductResDTO convertToOrderProductResDTO(Product orderProduct){
        return new OrderProductResDTO(id, orderProduct, orderCount);
    }
}
