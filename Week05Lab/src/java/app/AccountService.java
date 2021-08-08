/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import model.User;

/**
 *
 * @author 808111
 */
public class AccountService {

    private String username;
    private String password;

    public AccountService() {
    }

    public User login(String username, String password) {
        boolean nameCheck;
        boolean passwordCheck;
        if (username.equals("abe") || username.equals("barb")) {
            nameCheck = true;
        } else {
            nameCheck = false;
        }

        if (password.equals("password")) {
            passwordCheck = true;
            password = "";
        } else {
            passwordCheck = false;
        }

        if (nameCheck == true && passwordCheck == true) {
            User user = new User(username, password);
            return user;
        } else {
            return null;
        }
    }
}
