package com.levelmc.core.api.user;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import lombok.Getter;

import java.util.UUID;

public class BaseUser extends YamlConfig {

    @Path("uuid")
    @Getter
    private UUID uuid;

    @Path("name")
    @Getter
    private String name;

    public BaseUser(Player player) {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
    }

    public Player getPlayer() {
        return Server.getInstance().getPlayer(uuid).get();
    }

    public boolean isOnline() {
        return Server.getInstance().getPlayer(uuid).isPresent();
    }

}
