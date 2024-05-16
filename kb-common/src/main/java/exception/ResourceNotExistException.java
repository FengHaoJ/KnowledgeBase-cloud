package exception;

public class ResourceNotExistException extends BaseException{
    public ResourceNotExistException() {
    }

    public ResourceNotExistException(String msg) {
        super(msg);
    }
}
