package com.levelmc.core.api.yml.converter;


import com.levelmc.core.api.yml.ConfigSection;
import com.levelmc.core.api.yml.InternalConverter;
import com.levelmc.core.api.yml.YamlConfig;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

public class YamlConfigConverter implements Converter {
    private InternalConverter internalConverter;

    public YamlConfigConverter(InternalConverter internalConverter) {
        this.internalConverter = internalConverter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception {
        return (obj instanceof Map) ? obj : ((YamlConfig) obj).saveToMap(obj.getClass());
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        YamlConfig obj = (YamlConfig) newInstance(type);

        // Inject converter stack into subconfig
        for (Class aClass : internalConverter.getCustomConverters()) {
            obj.addConverter(aClass);
        }

        obj.loadFromMap((section instanceof Map) ? (Map) section : ((ConfigSection) section).getRawMap(), type);
        return obj;
    }

    // recursively handles enclosed classes
    public Object newInstance(Class type) throws Exception {
        Class enclosingClass = type.getEnclosingClass();
        if (enclosingClass != null) {
            Object instanceOfEnclosingClass = newInstance(enclosingClass);
            return type.getConstructor(enclosingClass).newInstance(instanceOfEnclosingClass);
        } else {
            return type.newInstance();
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return YamlConfig.class.isAssignableFrom(type);
    }
}
