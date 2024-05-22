package pda.shoppingmall.member;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.exception.DuplicateException;
import pda.shoppingmall.member.dto.LoginReqDTO;
import pda.shoppingmall.utils.ApiUtils;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {

//    private MemberService memberService;
    private MemberJPAService memberJPAService;

//    @GetMapping("/datasource")
//    public void makeConnection(){
//        memberService.makeConnection();
//    }

//    @PostMapping("/join/res/en") //Before
//    public ResponseEntity<String> joinByResponseEntity(@RequestBody MemberDTO memberDTO){
//
//        log.info("{}", member);
//
//        if(isDuplicateId(memberDTO)){
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("id 중복");
//        }
//
//        Member requestMember = new Member(
//                memberDTO.getUserId(),
//                memberDTO.getPw(),
//                memberDTO.getName(),
//                memberDTO.getEmail(),
//                memberDTO.getContact()
//        );
//
//        String userId = memberService.join(requestMember);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(userId);
//    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiUtils.ApiResult handleValidationException(MethodArgumentNotValidException errors){
//        Map<String, String> errorMap = new HashMap<>();
//
//        errors.getFieldErrors().forEach(fieldError -> {
//            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//        });
//
//        return ApiUtils.error(errorMap, HttpStatus.BAD_REQUEST);
//
//    }

    @PostMapping("/join") //After
    public ResponseEntity join(@Valid @RequestBody MemberDTO memberDTO){ //, Errors errors){

//        if(errors.hasErrors()){
//            Map<String, String> errorMap = new HashMap<>();
//
//            errors.getFieldErrors().forEach(fieldError -> {
//                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//            });
//
//            return ApiUtils.error(errorMap, HttpStatus.BAD_REQUEST);
//        }

        if(isDuplicateId(memberDTO)){
            throw new DuplicateException("아이디 중복");
        }

        Member requestMember = memberDTO.convertToEntity();

        String userId = memberJPAService.join(requestMember);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtils.success(userId));
    }

    private boolean isDuplicateId(MemberDTO memberDTO) {
        return memberJPAService.checkDuplicateId(memberDTO.getUserId());
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginReqDTO loginRequest){
        System.out.println(loginRequest);
        Member member = memberJPAService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiUtils.success(member));
    }

}
