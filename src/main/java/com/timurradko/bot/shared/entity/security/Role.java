package com.timurradko.bot.shared.entity.security;

import java.util.*;

public enum Role {
    ADMIN(2, Arrays.asList(Feature.SHOW_USERS_FOR_ADMIN,
            Feature.EDIT_USER,
            Feature.ADD_QUESTION,
            Feature.VIEW_ALL_TEST_QUESTIONS,
            Feature.CHANGE_USER_LEVEL,
            Feature.CHANGE_USER_STATUS,
            Feature.CHANGES_CHOOSE)),
    USER(1, new ArrayList<>()),
    BLOCKED(-1, new ArrayList<>());

    private Integer compare;
    private List<Feature> features;

    Role(Integer compare, List<Feature> features) {
        this.compare = compare;
        this.features = features;
    }

    public Integer getCompare() {
        return compare;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    private static Map<String, Role> ROLE_NAMES = new HashMap<>();

    public static Role getByName(String name) {
        return ROLE_NAMES.get(name);
    }

    static {
        ROLE_NAMES.put("admin", ADMIN);
        ROLE_NAMES.put("user", USER);
        ROLE_NAMES.put("blocked", BLOCKED);
    }
}
