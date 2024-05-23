package pda.shoppingmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pda.shoppingmall.product.Product;
import pda.shoppingmall.utils.PageNation;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class FindProductsResDTO {

    private PageNation pageNation;
    private List<Product> products;

}
