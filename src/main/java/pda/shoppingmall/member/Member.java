package pda.shoppingmall.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Member {

    private int id;
    private String userId;
    private String pw;
    private String name;
    private String email;
    private String contact;

    public Member(String userId, String pw, String name, String email, String contact) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    //    public static Member fromDtoToEntity(MemberDTO memberDTO){
//        return new Member(
//                memberDTO.getUserId(),
//                memberDTO.getPw(),
//                memberDTO.getName(),
//                memberDTO.getEmail(),
//                memberDTO.getContact()
//        );
//    }
}
