package pda.shoppingmall.member;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pda.shoppingmall.exception.DuplicateException;
import pda.shoppingmall.utils.ApiUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {

    private MemberService memberService;

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

        String userId = memberService.join(requestMember);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiUtils.success(userId));
    }

    private boolean isDuplicateId(MemberDTO memberDTO) {
        return memberService.checkDuplicateId(memberDTO.getUserId());
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
