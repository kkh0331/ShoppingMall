package pda.shoppingmall.member;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pda.shoppingmall.exception.NotCreateException;
import pda.shoppingmall.exception.PasswordNotValidException;
import pda.shoppingmall.member.dto.JoinReqDTO;
import pda.shoppingmall.member.dto.LoginReqDTO;
import pda.shoppingmall.member.repository.MemberRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MemberService {

    private MemberRepository memberRepository;

    @Transactional
    public String join(JoinReqDTO joinReqDTO){
        log.info("JoinReqDTO : {}", joinReqDTO);
        Member reqMember = joinReqDTO.convertToEntity();
        Member joinedMember = memberRepository.save(reqMember); //등록이 되지 않으면 null이 들어옴
        if(joinedMember == null){
            throw new NotCreateException("회원가입 도중에 서버 에러가 발생했습니다.");
        }
        return joinedMember.getUserId();
    }

    public boolean checkDuplicateId(String userId){
        Optional<Member> existMember = memberRepository.findByUserId(userId);
        return existMember.isPresent();
    }

    public String login(LoginReqDTO loginReqDTO) {
        Member loginMember = memberRepository.findByUserId(loginReqDTO.getUserId())
            .orElseThrow(() -> new NoSuchElementException("아이디가 존재하지 않습니다."));
        if(isRightPassword(loginReqDTO, loginMember))
            return loginMember.getName();
        else
            throw new PasswordNotValidException("비밀번호가 틀렸습니다.");
    }

    private static boolean isRightPassword(LoginReqDTO loginReqDTO, Member loginMember) {
        return loginMember.getName().equals(loginReqDTO.getPw());
    }

}
