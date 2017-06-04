package com.render;

import com.render.annotation.UseCase;

/**
 * Created by yg on 2017/4/19.
 */


public class PasswordUtils {

    @UseCase(id = "47", description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = "48")
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
}
