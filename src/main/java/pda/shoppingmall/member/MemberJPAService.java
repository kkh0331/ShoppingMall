package pda.shoppingmall.member;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Service;
import pda.shoppingmall.exception.CreateException;
import pda.shoppingmall.member.dto.JoinReqDTO;
import pda.shoppingmall.member.dto.LoginReqDTO;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MemberJPAService {

    private MemberJPARepository memberJPARepository;

    @Transactional
    public String join(JoinReqDTO joinReqDTO){
        log.info("JoinReqDTO : {}", joinReqDTO);
        Member reqMember = joinReqDTO.convertToEntity();
        memberJPARepository.save(reqMember);
        Optional<Member> savedMember = memberJPARepository.findByUserId(reqMember.getUserId());
        log.info("savedMember : {}", savedMember);
        if(savedMember.isEmpty()){
            throw new CreateException("회원가입 도중에 서버 에러가 발생했습니다.");
        }
        return savedMember.get().getUserId();
    }

    public boolean checkDuplicateId(String userId){
        Optional<Member> existMember = memberJPARepository.findByUserId(userId);
        return existMember.isPresent();
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
