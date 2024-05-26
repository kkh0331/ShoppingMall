package pda.shoppingmall.exception;

public class NotMatchMemberException extends RuntimeException{

    private String message;

    public NotMatchMemberException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
