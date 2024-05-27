package pda.shoppingmall.member;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.exception.DuplicateMemberIdException;
import pda.shoppingmall.member.dto.LoginReqDTO;
import pda.shoppingmall.member.dto.JoinReqDTO;
import pda.shoppingmall.utils.ApiUtils;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody JoinReqDTO joinReqDTO){

        log.info("JoinReqDTO : {}", joinReqDTO);

        if(isDuplicateId(joinReqDTO.getUserId())){
            throw new DuplicateMemberIdException("아이디 중복");
        }

        String userId = memberService.join(joinReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtils.success(userId));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginReqDTO loginRequest){
        log.info("LoginRequest : {}", loginRequest);
        String loginMemberName = memberService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiUtils.success(loginMemberName));
    }

    // 아이디 중복 확인 -> 메시지
    @PostMapping("/check/userId")
    public ResponseEntity checkUsableUserId(@RequestBody String userId){
        log.info("userId : {}", userId);
        if(isDuplicateId(userId))
            throw new DuplicateMemberIdException("이미 사용 중인 아이디입니다.");
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiUtils.success("사용 가능한 아이디입니다."));
    }

    private boolean isDuplicateId(String userId) {
        return memberService.checkDuplicateId(userId);
    }

}
