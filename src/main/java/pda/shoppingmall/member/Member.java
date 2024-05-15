package pda.shoppingmall.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Member {

    private String name;
    @JsonProperty("user_id")
    private String userId;
    private String pw;
    private String email;
    private String contact;

}
