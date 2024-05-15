package pda.shoppingmall.exception;

public class DuplicateException extends RuntimeException{

    private String message;

    public DuplicateException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
