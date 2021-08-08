package services;

import dataaccess.ItemDB;
import java.util.List;
import models.Item;

public class InventoryService {

    public List<Item> getAllItems(String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(email);
        return items;
    }

    public Item get(int itemId, String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(email);
        boolean check = false;
        for (Item i : items) {
            if (i.getItemId() == itemId) {
                check = true;
            }
        }
        if (check == false) {
            throw new Exception();
        }
        Item item = itemDB.get(itemId, email);
        return item;
    }

    public void insert(int category, String itemName, double price, String owner) throws Exception {
        Item item = new Item(0, category, itemName, price, owner);
        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
    }

    public void update(int itemId, int category, String itemName, double price, String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(email);
        boolean check = false;
        for (Item i : items) {
            if (i.getItemId() == itemId) {
                check = true;
            }
        }
        if (check == false) {
            throw new Exception();
        }
        Item item = new Item();
        item.setItemId(itemId);
        item.setCategory(category);
        item.setItemName(itemName);
        item.setPrice(price);
        item.setOwner(email);
        itemDB.update(item);
    }

    public void delete(int itemId, String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(email);
        boolean check = false;
        for (Item i : items) {
            if (i.getItemId() == itemId) {
                check = true;
            }
        }
        if (check == false) {
            throw new Exception();
        }
        Item item = new Item();
        item.setItemId(itemId);
        item.setOwner(email);
        itemDB.delete(item);
    }

    public List<Item> searchByAdmin(String keyword) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.searchByAdmin(keyword);
        return items;
    }

    public List<Item> searchByCompanyAdmin(String keyword, int companyId) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.searchByCompanyAdmin(keyword, companyId);
        return items;
    }
}
