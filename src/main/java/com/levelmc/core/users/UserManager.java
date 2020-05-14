package com.levelmc.core.users;

import com.levelmc.core.api.user.BaseUserManager;

public class UserManager extends BaseUserManager<User> {
    public UserManager() {
        super(User.class);
    }
}
