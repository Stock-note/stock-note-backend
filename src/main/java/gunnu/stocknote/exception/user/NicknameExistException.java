package gunnu.stocknote.exception.user;

public class NicknameExistException extends RuntimeException {

    public NicknameExistException(String message) {
        super(message);
    }

    public NicknameExistException() {
        super("해당 닉네임이 이미 존재합니다.");
    }
}
