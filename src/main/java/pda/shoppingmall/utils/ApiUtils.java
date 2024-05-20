package pda.shoppingmall.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiUtils<T> {

    public static<T> ApiResult<T> success(T data){
        return new ApiResult<>(true, data, null);
    }

    public static<M> ApiResult<M> error(M message, HttpStatus httpStatus){
        return new ApiResult<>(false, null, new ApiError(message, httpStatus));
    }

    @Getter
    @AllArgsConstructor
    public static class ApiResult<T> {

        private boolean success;
        private T response;
        private ApiError error;

    }

    @Getter
    @AllArgsConstructor
    private static class ApiError<T>{
        private T message;
        private HttpStatus httpStatus;
    }
}
