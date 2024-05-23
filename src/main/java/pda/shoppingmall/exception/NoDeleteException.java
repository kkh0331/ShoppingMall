package pda.shoppingmall.exception;

public class NoDeleteException extends RuntimeException{

    private String message;

    public NoDeleteException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
