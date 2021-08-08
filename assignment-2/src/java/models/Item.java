package models;

import java.io.Serializable;

public class Item implements Serializable {

    private int itemId;
    private int category;
    private String itemName;
    private double price;
    private String owner;

    public Item() {

    }

    public Item(int itemId, int category, String itemName, double price, String owner) {
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
        this.owner = owner;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItem_name(String item_name) {
        this.itemName = item_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
