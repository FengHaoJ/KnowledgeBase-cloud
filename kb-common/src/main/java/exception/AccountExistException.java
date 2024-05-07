package exception;


/**
 * 账号存在异常
 */
public class AccountExistException extends BaseException{
    public AccountExistException() {
    }

    public AccountExistException(String msg) {
        super(msg);
    }
}
