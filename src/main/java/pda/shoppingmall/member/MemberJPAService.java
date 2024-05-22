package pda.shoppingmall.member;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pda.shoppingmall.member.dto.LoginReqDTO;

@Service
@AllArgsConstructor
@Slf4j
public class MemberJPAService {

    private MemberJPARepository memberJPARepository;

    @Transactional
    public String join(Member member){
        memberJPARepository.save(member);
        Member savedMember = memberJPARepository.findByUserId(member.getUserId()).orElse(null);
        return savedMember.getUserId();
    }

    public boolean checkDuplicateId(String userId){
        Member existMember = memberJPARepository.findByUserId(userId).orElse(null);
        return existMember != null;
    }

    public Member login(LoginReqDTO loginReqDTO) {
        Member resMember = memberJPARepository.findByUserId(loginReqDTO.getUserId()).orElse(null);
        if(isMatchMember(resMember, loginReqDTO)){
            return resMember;
        }
        return null;
    }

    private boolean isMatchMember(Member resMember, LoginReqDTO loginReqDTO){
        return resMember != null && loginReqDTO.getPw().equals(resMember.getPw());
    }

}
