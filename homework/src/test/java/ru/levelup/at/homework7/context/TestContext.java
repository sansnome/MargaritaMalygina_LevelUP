package ru.levelup.at.homework7.context;

import java.util.HashMap;
import  java.util.Map;

public class TestContext {
    private static TestContext instanse;

    private final Map<String, Object> context;

    private TestContext() {
        this.context = new HashMap<>();
    }

    public void putObject(String key, Object o) {
        context.put(key, o);
    }

    public Object getObject(String key) {
        return context.get(key);
    }

    public static TestContext getInstance() {
        if (instanse == null) {
            instanse = new TestContext();
        }
        return instanse;
    }

    public static void clear() {
        instanse = null;
    }
}
