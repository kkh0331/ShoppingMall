package pda.shoppingmall.exception;

public class DeleteException extends RuntimeException{

    private String message;

    public DeleteException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
