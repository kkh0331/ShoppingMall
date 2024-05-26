package pda.shoppingmall.exception;

public class NotDeleteException extends RuntimeException{

    private String message;

    public NotDeleteException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
