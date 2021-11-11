package io.github.bael.part1.hash;

import java.util.HashMap;
import java.util.Map;

public class Coin {

    private int nominal;

    public Coin(int nominal) {
        this.nominal = nominal;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Coin)) {
            return false;
        }

        return ((Coin) obj).nominal == nominal;
    }

    @Override
    public int hashCode() {
        return nominal;
    }


    public static void main(String[] args) {
        Map<Coin, Integer> cache = new HashMap<>();

        // первая монета
        Coin five = new Coin(5);
        cache.put(five, 1);
//        five.nominal = 7;
        cache.put(five, 2);

        Coin five3 = new Coin(5);
        cache.put(five3, 3);
        System.out.println(cache);
//
//        // вторая
//        // якобы такая же, монета
//        Coin fiveTwo = new Coin(6);
//        cache.put(five, 1);
//        System.out.println("Add coin (5) first time " + cache);
//
//        cache.put(fiveTwo, 5);
//        System.out.println("Add coin (5) second time " + cache);
//
//
//        cache.remove(new Coin(5));
//        //
//        System.out.println("Trying to remove the coin 5 " + cache);
//
//        int hash1 = Objects.hash(five);
//        int hash2 = Objects.hash(fiveTwo);
//        System.out.println(hash1 + " & " + hash2 + " are equal? " + (hash1 == hash2));
//
//        hash1 = Objects.hash(five.nominal);
//        hash2 = Objects.hash(fiveTwo.nominal);
//        System.out.println(hash1 + " & " + hash2 + " are equal now ? " + (hash1 == hash2));

    }
}
