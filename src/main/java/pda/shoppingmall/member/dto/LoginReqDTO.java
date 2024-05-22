package pda.shoppingmall.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginReqDTO {

    @JsonProperty("user_id")
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String pw;

}
