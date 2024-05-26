package pda.shoppingmall.member;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.exception.DuplicateException;
import pda.shoppingmall.member.dto.LoginReqDTO;
import pda.shoppingmall.member.dto.JoinReqDTO;
import pda.shoppingmall.utils.ApiUtils;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {

    private MemberJPAService memberJPAService;

    @PostMapping("/join") //After
    public ResponseEntity join(@Valid @RequestBody JoinReqDTO joinReqDTO){

        log.info("JoinReqDTO : {}", joinReqDTO);

        if(isDuplicateId(joinReqDTO)){
            throw new DuplicateException("아이디 중복");
        }

        String userId = memberJPAService.join(joinReqDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtils.success(userId));
    }

    private boolean isDuplicateId(JoinReqDTO joinReqDTO) {
        return memberJPAService.checkDuplicateId(joinReqDTO.getUserId());
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginReqDTO loginRequest){
        Member member = memberJPAService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiUtils.success(member));
    }

}
