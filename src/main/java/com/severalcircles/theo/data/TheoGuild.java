package com.severalcircles.theo.data;

import com.severalcircles.theo.data.user.TheoUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheoGuild {
    String id;
    Map<String, List<String>> permissions;
    public TheoGuild(String id, Map<String, List<String>> permissions) {
        this.id = id;
        this.permissions = permissions;
    }

    public TheoGuild() {
        this.permissions = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public List<TheoUser> getUsers() {
        return TheoDataManager.getAllForGuild(id);
    }

    public Map<String, List<String>> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, List<String>> permissions) {
        this.permissions = permissions;
    }
    public void addPermission(String role, String permission) {
        permissions.computeIfAbsent(role, k -> new ArrayList<>());
        permissions.get(role).add(permission);
    }
}
