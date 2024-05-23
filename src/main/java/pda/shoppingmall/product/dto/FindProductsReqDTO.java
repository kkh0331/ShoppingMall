package pda.shoppingmall.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class FindProductsReqDTO {

    @NotNull
    @Min(value = 1, message = "limit은 1 이상이어야 합니다.")
    private int limit;

    @NotNull
    @Min(value = 0, message = "currentPage는 1 이상이어야 합니다.")
    private int currentPage;

    @Null
    @Min(value = 1, message = "categoryId는 1 이상이어야 합니다.")
    private Integer categoryId;

}
