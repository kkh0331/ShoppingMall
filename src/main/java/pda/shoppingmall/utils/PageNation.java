package pda.shoppingmall.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class PageNation {

    private int currentPage;
    private Long totalCount;

}
