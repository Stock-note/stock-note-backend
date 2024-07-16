package gunnu.stocknote.exception.user;

public class ExistUsernameException extends RuntimeException {

    public ExistUsernameException(String message) {
        super(message);
    }

    public ExistUsernameException() {
        super("해당 닉네임이 이미 존재합니다.");
    }
}
