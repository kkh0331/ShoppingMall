package pda.shoppingmall.exception;

public class DuplicateMemberIdException extends RuntimeException{

    private String message;

    public DuplicateMemberIdException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
