package com.levelmc.core.users;

import com.levelmc.core.api.user.BaseUserManager;

public class UserManager extends BaseUserManager<User> {
    //todo implement serialization of user data.

    public UserManager() {
        super(User.class);
    }


}
