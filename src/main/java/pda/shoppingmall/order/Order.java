package pda.shoppingmall.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pda.shoppingmall.product.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@ToString
//@RequiredArgsConstructor
public class Order {

    private int id;

    private Product pruduct; // Domain-bjec

    @JsonProperty("order_count")
    private int count;

    public Order(Product orderedProduct, int count) {
        this.pruduct = orderedProduct;
        this.count = count;
    }

    public void setId(int id){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        this.id = Integer.parseInt(dateFormat.format(new Date())+String.format("%02d", id));
    }

}
