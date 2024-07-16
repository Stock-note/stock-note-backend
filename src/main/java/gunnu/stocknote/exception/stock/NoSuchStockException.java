package gunnu.stocknote.exception.stock;

public class NoSuchStockException extends RuntimeException {

    public NoSuchStockException() {
        super("일치하는 주식이 존재하지 않습니다");
    }
}
