import model.Curr;
import model.Gamer;
import model.Inv;
import model.Item;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    private final Curr gold = new Curr("gold", 0L);
    private final Curr silver = new Curr("silver", 0L);
    private final Gamer gamer1 = new Gamer(UUID.randomUUID().toString(), "Psycho");
    private final Gamer gamer2 = new Gamer(UUID.randomUUID().toString(), "Killer");

    private final Item gift = new Item("Super Box", 10L, gold);

    public void createData() {

        Item item0 = new Item("box", 100L, silver);
        Item item1 = new Item("bigbox", 100L, gold);
        Item item3 = new Item("uberbox", 10000L, silver);

        Inv inventory1 = new Inv("name1Inv",
                Stream.of(
                        new AbstractMap.SimpleEntry<>(item0, 10L),
                        new AbstractMap.SimpleEntry<>(item1, 35L))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));


        Inv inventory2 = new Inv("name2Inv",
                Stream.of(
                        new AbstractMap.SimpleEntry<>(item3, 42L))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        gamer1.setInv(inventory1);
        gamer2.setInv(inventory2);
    }

    public void printInv(Gamer gamer) throws InterruptedException {
        for (Map.Entry<Item, Long> entry : gamer.getInv().getInventory().entrySet()) {
            System.out.printf("Item %s, %d pcs, it costs %d %s per 1 pcs and %d %s total \n",
                    entry.getKey().getName(),
                    entry.getValue(),
                    entry.getKey().getCost(),
                    entry.getKey().getCurrency().getName(),
                    entry.getValue() * entry.getKey().getCost(),
                    entry.getKey().getCurrency().getName());
        }
    }

    public boolean buySuperDuperBox(Item item, Long count, Gamer buyer, Curr curr) {
        if (curr.equals(item.getCurrency())) {
            Long sum = item.getCost() * count;
            if (curr.getName().equals("gold")) {
                if (buyer.getWallet().getGold() > sum) {
                    buyer.getWallet().setGold(buyer.getWallet().getGold() - sum);
                    buyer.getInv().getInventory().put(item, count);
                    System.out.printf("%s bought %s and spend %d %s", buyer.getName(), item.getName(), sum, curr.getName());
                    return true;
                } else {
                    System.out.println("Not enough money!");
                    return false;
                }
            } else if (curr.getName().equals("silver")) {
                if (buyer.getWallet().getSilver() > sum) {
                    buyer.getWallet().setSilver(buyer.getWallet().getSilver() - sum);
                    buyer.getInv().getInventory().put(item, count);
                    System.out.printf("%s bought %s and spend %d %s", buyer.getName(), item.getName(), sum, curr.getName());
                    return true;
                } else {
                    System.out.println("Not enough money!");
                    return false;
                }
            }

        } else {
            System.out.println("Ooops... So sad, but your money is ineligible. Try again later!");
            return false;
        }
        return false;
    }

    public boolean giveGift(Item item, Long count, Gamer donor, Gamer recipient) {
        Long eqDonor = donor.getInv().getInventory().get(item) > 0 ? donor.getInv().getInventory().get(item) : 0L;
        Long eqRecipient = recipient.getInv().getInventory().get(item) != null ? recipient.getInv().getInventory().get(item) : 0L;
        if (eqDonor >= count) {
            donor.getInv().getInventory().put(item, eqDonor - count);
            if (donor.getInv().getInventory().get(item) == 0) donor.getInv().getInventory().remove(item);
            recipient.getInv().getInventory().put(item, eqRecipient + count);
            System.out.println("Gifted! =)");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        Main game = new Main();
        Gamer g1 = game.gamer1;
        Gamer g2 = game.gamer2;
        Curr gold = game.gold;
        Curr silver = game.silver;
        System.out.println("Let's play a game! ))");
        Thread.sleep(2000);

        System.out.println("Wait a second... I create players.\n");
        game.createData();

        System.out.printf("This is gamer %s, he have id: %s \n", g1.getName(), g1.getId());
        System.out.printf("this is gamer %s, he have id: %s \n\n", g2.getName(), g2.getId());
        Thread.sleep(2000);
        System.out.println("Let's check gamers inventory!)");
        Thread.sleep(2000);
        System.out.printf("Gamer %s have: \n", g1.getName());
        game.printInv(g1);
        System.out.printf("And gamer %s have: \n\n", g2.getName());
        game.printInv(g2);
        Thread.sleep(2000);

        System.out.printf("And also they have a some money.\n%s have %d silver and %d gold \n", g1.getName(), g1.getWallet().getSilver(), g1.getWallet().getGold());
        System.out.printf("%s have %d silver and %d gold \n\n", g2.getName(), g2.getWallet().getSilver(), g2.getWallet().getGold());
        System.out.printf("%s wants buy new super box and give a gift to %s \n", g2.getName(), g1.getName());
        Thread.sleep(2000);

        if (game.buySuperDuperBox(game.gift, 1L, g2, silver)) {
            System.out.println("Yep! Now he can give a gift!");
        } else {
            System.out.println("Unfortunately, he cannot buy a gift :(");
        } 
        System.out.println("Ouch, let's try again!");
        Thread.sleep(2000);

        if (game.buySuperDuperBox(game.gift, 2L, g2, gold)) {
            System.out.println("Yep! Now he can give a gift!");
        } else {
            System.out.println("Unfortunately, he cannot buy a gift :(");
        }

        System.out.printf("%s have %d silver and %d gold \n", g1.getName(), g1.getWallet().getSilver(), g1.getWallet().getGold());
        System.out.printf("%s have %d silver and %d gold \n", g2.getName(), g2.getWallet().getSilver(), g2.getWallet().getGold());

        Thread.sleep(2000);
        System.out.printf("Gamer %s have: \n", g1.getName());
        game.printInv(g1);
        System.out.printf("And gamer %s have: \n", g2.getName());
        game.printInv(g2);
        Thread.sleep(2000);

        System.out.printf("%s got an item and time to give it to %s! \n", g2.getName(), g1.getName());

        if (game.giveGift(game.gift, 2L, g2, g1)){
            System.out.printf("%s is very happy! His friend gave his a gift! \n", g1.getName());

        }

        Thread.sleep(2000);
        System.out.println("Let's check last time inventories!");
        System.out.printf("Gamer %s have: \n", g1.getName());
        game.printInv(g1);
        System.out.printf("And gamer %s have: \n", g2.getName());
        game.printInv(g2);

    }
}
