package pda.shoppingmall.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;
import pda.shoppingmall.product.Product;

@Getter
@ToString
public class RegisterProductReqDTO {

    @NotBlank(message = "상품 이름은 필수 입력입니다.")
    private String name;

    @Min(0)
    private int price;

    private String description;

    @JsonProperty("category_id")
    private Integer categoryId;

    public Product convertToEntity(){
        return new Product(name, price, description, categoryId);
    }
}
