package pda.shoppingmall.exception;

public class CreateException extends RuntimeException{

    private String message;

    public CreateException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
