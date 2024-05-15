package pda.shoppingmall.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.utils.ApiUtils;

import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {

    private MemberService memberService;

    @PostMapping("/join/res/en") //Before
    public ResponseEntity<String> joinByResponseEntity(@RequestBody Member member){

        log.info("{}", member);

        if(isDuplicateId(member)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("id 중복");
        }

        String userId = memberService.join(member);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userId);
    }

    @PostMapping("/join/api/result") //After
    public ResponseEntity joinByApiResult(@RequestBody Member member){

        log.info("{}", member);

        if(isDuplicateId(member)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiUtils.error("아이디 중복", HttpStatus.CONFLICT));
        }

        String userId = memberService.join(member);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtils.success(userId));
    }

    private boolean isDuplicateId(Member member) {
        return memberService.checkDuplicateId(member.getUserId());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest){
        String userId = loginRequest.get("userId");
        String pw = loginRequest.get("pw");
        //TODO 입력받은 값 검증
        String jwt = memberService.login(userId, pw);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
