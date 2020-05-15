package com.levelmc.core.api.user;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.YamlConfig;
import org.joor.Reflect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseUserManager<T extends BaseUser> implements Listener {

    private Map<UUID, T> users = new HashMap<>();

    private Class<T> userClass;

    public BaseUserManager(Class<T> userClass) {
        this.userClass = userClass;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        T user = Reflect.onClass(userClass).create(e.getPlayer()).get();
        users.put(user.getUuid(), user);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {

        Player player = e.getPlayer();

        if (!users.containsKey(player.getUniqueId())) {
            return;
        }

        users.remove(player.getUniqueId());
    }

    public boolean hasData(Player player) {
        return users.containsKey(player.getUniqueId());
    }

    public T getUser(Player player) {
        return users.get(player.getUniqueId());
    }

    public T getUser(UUID uid) {
        return users.get(uid);
    }

}
