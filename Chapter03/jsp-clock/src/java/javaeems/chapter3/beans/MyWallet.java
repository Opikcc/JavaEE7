
package javaeems.chapter3.beans;

import java.util.*;

public class MyWallet {
    String[] coins = {"1c", "1c", "5c", "25c", "25c"};
    String[] notes = {"1$", "1$", "1$", "5$", "10$", "10$", "20$"};
    String[] receipts = {"gas - $42.50", "groceries - $35.26", "bookstore - $12.99"};
    
    public String[] getCoins() {
        return coins;
    }
    
    public List getNotes() {
        return Arrays.asList(notes);
    }
    
    public Set getReceipts() {
        return new HashSet(Arrays.asList(receipts));
    }
    
}
