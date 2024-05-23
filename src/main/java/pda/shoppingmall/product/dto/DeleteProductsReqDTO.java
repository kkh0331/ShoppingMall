package pda.shoppingmall.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class DeleteProductsReqDTO {

    @Size(min = 1, message = "삭제할 데이터를 최소 1개 이상을 선택해야 합니다.")
    private List<Long> productIds;

}
