package pda.shoppingmall.exception;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pda.shoppingmall.utils.ApiUtils;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult handleValidationException(MethodArgumentNotValidException errors){
        Map<String, String> errorMap = new HashMap<>();

        errors.getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        log.info("errors : {}", errors.toString());

        return ApiUtils.error(errorMap, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiUtils.ApiResult  handleDuplicateException(DuplicateException error){
        return ApiUtils.error(error.getMessage(), HttpStatus.CONFLICT);
    }

}
