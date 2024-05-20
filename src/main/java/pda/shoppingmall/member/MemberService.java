package pda.shoppingmall.member;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    public String join(Member member){
        return memberRepository.save(member);
    }

    public boolean checkDuplicateId(String userId){
        Member existMember = memberRepository.findById(userId);
        return existMember != null;
    }

    public String login(String userId, String pw) {
        return "jwt";
    }

}
