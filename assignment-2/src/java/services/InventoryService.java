package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import java.util.List;
import models.Category;
import models.Item;

public class InventoryService {

    public List<Item> getAllItems(String email) throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll(email);
        return items;
    }

    public void insert(int category, String itemName, double price, String owner) throws Exception {
        Item item = new Item(0, category, itemName, price, owner);
        ItemDB itemDB = new ItemDB();
        itemDB.insert(item);
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

    public String getCategoryName(int id) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(id);
        String categoryName = category.getCategoryName();
        return categoryName;
    }

    public List<Category> getAllCategories() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categories = categoryDB.getAll();
        return categories;
    }
}
