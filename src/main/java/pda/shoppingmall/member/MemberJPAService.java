package pda.shoppingmall.member;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pda.shoppingmall.exception.NotCreateException;
import pda.shoppingmall.exception.NotMatchMemberException;
import pda.shoppingmall.member.dto.JoinReqDTO;
import pda.shoppingmall.member.dto.LoginReqDTO;

import java.util.NoSuchElementException;
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
            throw new NotCreateException("회원가입 도중에 서버 에러가 발생했습니다.");
        }
        return savedMember.get().getUserId();
    }

    public boolean checkDuplicateId(String userId){
        Optional<Member> existMember = memberJPARepository.findByUserId(userId);
        return existMember.isPresent();
    }

    public Member login(LoginReqDTO loginReqDTO) {
        Optional<Member> resMember = memberJPARepository.findByUserId(loginReqDTO.getUserId());
        if(resMember.isEmpty()){
            throw new NoSuchElementException("해당 아이디를 찾을 수 없습니다. 다시 입력해 주세요");
        }
        if(isMatchMember(resMember.get(), loginReqDTO)){
            return resMember.get();
        }
        throw new NotMatchMemberException("비밀번호가 일치하지 않습니다. 다시 입력해 주세요");
    }

    private boolean isMatchMember(Member resMember, LoginReqDTO loginReqDTO){
        return loginReqDTO.getPw().equals(resMember.getPw());
    }

}
