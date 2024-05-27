package pda.shoppingmall.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pda.shoppingmall.utils.ApiUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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
    public ApiUtils.ApiResult  handleDuplicateMemberIdException(DuplicateMemberIdException error){
        return ApiUtils.error(error.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult handleTypeMismatchException(TypeMismatchException error){
        String requiredType = error.getRequiredType().getSimpleName();
        String errorType =  error.getValue().getClass().getSimpleName();
        String errorMessage = String.format("%s 타입이 들어와야 되는데 %s 타입이 들어왔습니다.", requiredType, errorType);
        return ApiUtils.error(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiUtils.ApiResult handleNoSuchElementException(NoSuchElementException error){
        return ApiUtils.error(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiUtils.ApiResult handleNotDeleteException(NotDeleteException error){
        return ApiUtils.error(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiUtils.ApiResult handleNotCreateException(NotCreateException error){
        return ApiUtils.error(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult handleNotMatchMemberException(NotMatchMemberException error){
        return ApiUtils.error(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
