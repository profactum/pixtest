package model;


public class Wallet {
    private final Curr silver = new Curr("silver",0L);
    private final Curr gold = new Curr("gold",0L); ;

    public Long getSilver() {
        return silver.getCount();
    }

    public void setSilver(Long silver) {
        this.silver.setCount(silver);
    }

    public Long getGold() {
        return gold.getCount();
    }

    public void setGold(Long gold) {
        this.gold.setCount(gold);
    }

    public Wallet(Long silver, Long gold) {
        this.silver.setCount(silver);
        this.gold.setCount(gold);
    }
}
