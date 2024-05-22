package pda.shoppingmall.member;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pda.shoppingmall.member.dto.LoginReqDTO;

@Service
@AllArgsConstructor
@Slf4j
public class MemberService {

    private MemberRepository memberRepository;

    @Transactional
    public String join(Member member){
        memberRepository.save(member);
        // TODO 검증 필요;
        log.info("save 후");
        Member savedMember = memberRepository.findByUserId(member.getUserId());
        log.info("savedMember : {}", savedMember);
        return savedMember.getUserId();
    }

    public boolean checkDuplicateId(String userId){
        Member existMember = memberRepository.findByUserId(userId);
        return existMember != null;
    }

    public Member login(LoginReqDTO loginReqDTO) {
        //TODO 예외 처리 적용

        Member resMember = memberRepository.findByUserId(loginReqDTO.getUserId());
        if(isMatchMember(resMember, loginReqDTO)){
            return resMember;
        }
        return null;
    }

    private boolean isMatchMember(Member resMember, LoginReqDTO loginReqDTO){
        return resMember != null && loginReqDTO.getPw().equals(resMember.getPw());
    }

//    public void makeConnection() {
//        memberRepository.makeConnection();
//    }
}
