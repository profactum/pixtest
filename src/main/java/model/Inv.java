package model;

import java.util.HashMap;
import java.util.Map;

public class Inv {
    private String name;
    private Map<Item, Long> inventory;

    public Inv(String name, Map<Item, Long> inv) {
        this.name = name;
        this.inventory = inv;
    }

    public Inv() {
        this.name = "main";
        this.inventory = new HashMap<Item, Long>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Item, Long> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Item, Long> inventory) {
        this.inventory = inventory;
    }
}
