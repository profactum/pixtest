package model;

public class Gamer {
    private String id;
    private String name;
    private Inv inv;
    private Wallet wallet;


    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Gamer(String id, String name, Inv inv, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.inv = inv;
        this.wallet = wallet;
    }

    public Gamer(String id, String name, Inv inv) {
        this.id = id;
        this.name = name;
        this.inv = inv;
        this.wallet = new Wallet(10000L,100L);
    }

    public Gamer(String id, String name) {
        this.id = id;
        this.name = name;
        this.inv = new Inv();
        this.wallet = new Wallet(10000L,100L);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inv getInv() {
        return inv;
    }

    public void setInv(Inv inv) {
        this.inv = inv;
    }
}
