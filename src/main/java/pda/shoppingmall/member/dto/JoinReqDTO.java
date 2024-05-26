package pda.shoppingmall.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pda.shoppingmall.member.Member;

@Getter
@ToString
public class JoinReqDTO {

    private int id;

    @JsonProperty("user_id")
    @NotBlank(message = "아이디는 필수 입력입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, message = "최소 8자리 이상 입력해주세요.")
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$", message = "비밀번호는 최소 8자에서 16자까지, 영문자, 숫자 및 특수 문자를 포함해야 합니다.")
    private String pw;

    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "연락처는 필수입력입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 연락처 형식이 아닙니다.")
    private String contact;

    public Member convertToEntity(){
        return new Member(userId, pw, name, email, contact);
    }

}
