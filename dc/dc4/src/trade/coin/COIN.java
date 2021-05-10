package src.trade.coin;

public enum COIN {
    BITCOIN("btc_jpy"),
    ;
    private String coinPair;
    private COIN(String coinPair){
        this.coinPair = coinPair;
    }

    public String getCoinPair() {
        return coinPair;
    }
}
