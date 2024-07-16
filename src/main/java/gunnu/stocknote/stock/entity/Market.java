package gunnu.stocknote.stock.entity;

import lombok.Getter;

@Getter
public enum Market {
    KOSPI("KOSPI"),
    KOSDAQ("KOSDAQ");

    private String market;

    Market(String market) {
        this.market = market;
    }
}
