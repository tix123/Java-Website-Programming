package services;

import dataaccess.CompanyDB;
import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.ArrayList;
import java.util.List;
import models.Company;
import models.Item;
import models.Role;
import models.User;

public class AccountService {

    public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);

            if (!user.isActive()) {
                return null;
            }

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

    public List<User> getAllByCompany(int companyId) throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAllByCompany(companyId);
        return users;
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }

    public User getByCompanyAdmin(String email, int companyId) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        if (user.getCompany() == companyId) {
            return user;
        } else {
            throw new Exception();
        }
    }

    public void insert(String userEmail, String firstName, String lastName, String password, int company) throws Exception {
        User user = new User(userEmail, true, firstName, lastName, password, 2, company);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String userEmail, boolean active, String firstName, String lastName, String password, int role, int company) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(userEmail);
        user.setActive(active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        user.setCompany(company);
        userDB.update(user);
    }

    public void updateByCompanyAdmin(String userEmail, boolean active, String firstName, String lastName, String password, int role, int companyId) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(userEmail);
        if (user.getCompany() == companyId) {
            user.setActive(active);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setRole(role);
            user.setCompany(companyId);
            userDB.update(user);
        } else {
            throw new Exception();
        }
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

    public void deleteByCompanyAdmin(String email, int companyId) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        if (user.getCompany() == companyId) {
            InventoryService is = new InventoryService();
            List<Item> items = is.getAllItems(email);
            for (Item i : items) {
                is.delete(i.getItemId(), email);
            }
            userDB.delete(user);
        } else {
            throw new Exception();
        }
    }

    public String getRoleName(int id) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(id);
        String roleName = role.getRoleName();
        return roleName;
    }

    public List<Role> getAllRoles() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }

    public List<Role> getAllRolesByCompanyAdmin() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = new ArrayList<>();
        roles.add(roleDB.get(2));
        roles.add(roleDB.get(3));
        return roles;
    }

    public String getCompanyName(int id) throws Exception {
        CompanyDB companyDB = new CompanyDB();
        Company company = companyDB.get(id);
        String companyName = company.getCompanyName();
        return companyName;
    }

    public List<Company> getAllCompanys() throws Exception {
        CompanyDB companyDB = new CompanyDB();
        List<Company> companys = companyDB.getAll();
        return companys;
    }
}
