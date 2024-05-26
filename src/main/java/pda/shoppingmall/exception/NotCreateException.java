package pda.shoppingmall.exception;

public class NotCreateException extends RuntimeException{

    private String message;

    public NotCreateException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
