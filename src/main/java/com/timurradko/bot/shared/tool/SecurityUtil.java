package com.timurradko.bot.shared.tool;

import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.entity.security.Role;

import java.util.List;

public class SecurityUtil {
    public static boolean hasFeature(User user, Feature feature) {
        Role role = user.getRole();
        if (role.equals(Role.BLOCKED)) {
            return false;
        }
        List<Feature> features = user.getRole().getFeatures();
        return features.contains(feature);
    }
}
