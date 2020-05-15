package com.levelmc.core.config;

import com.levelmc.core.Core;
import com.levelmc.core.api.yml.Path;
import com.levelmc.core.api.yml.Skip;
import com.levelmc.core.api.yml.YamlConfig;

import java.util.HashMap;
import java.util.Map;

public class CoreConfig extends YamlConfig {

    @Path("messages")
    private Map<String, String> messages = new HashMap<>();

    @Skip
    private Core parent;

    public CoreConfig(Core config) {
        this.parent = config;
    }

    public String getMessage(String key) {
        return messages.get(key);
    }

    public String getMessage(String key, String defaultValue) {
        if (!messages.containsKey(key)) {
            messages.put(key, defaultValue);
            return defaultValue;
        }

        return messages.get(key);
    }

}
