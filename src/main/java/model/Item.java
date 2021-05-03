package model;

public class Item {
    private String name;
    private Long cost;
    private Curr currency;

    public Item(String name, Long cost, Curr currency) {
        this.name = name;
        this.cost = cost;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Curr getCurrency() {
        return currency;
    }

    public void setCurrency(Curr currency) {
        this.currency = currency;
    }
}
