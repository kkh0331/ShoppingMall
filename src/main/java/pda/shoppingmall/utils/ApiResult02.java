package pda.shoppingmall.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResult02<T> {

    private boolean success;
    private T response;
    private ApiError error;

    public static <T> ApiResult02<T> success(T data){
        return new ApiResult02<>(true, data, null);
    }

    public static ApiResult02 error(String message, HttpStatus httpStatus){
        return new ApiResult02<>(false, null, new ApiError(message, httpStatus));
    }

    @Getter
    @AllArgsConstructor
    static class ApiError{
        String message;
        HttpStatus httpStatus;
    }

}