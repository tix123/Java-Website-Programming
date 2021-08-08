package services;

import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Item;
import models.User;

public class AccountService {

    public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword()) && user.isActive()) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public void insert(String userEmail, String firstName, String lastName, String password) throws Exception {
        User user = new User(userEmail, true, firstName, lastName, password, 2);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String userEmail, String firstName, String lastName, String password) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(userEmail);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        userDB.update(user);
    }

    public void delete(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        InventoryService is = new InventoryService();
        List<Item> items = is.getAllItems(email);
        for (Item i : items) {
            is.delete(i.getItemId(), email);
        }
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
}
