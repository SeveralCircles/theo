package com.severalcircles.theo.data.guild;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.user.TheoUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheoGuild {
    String id;
    public TheoGuild(String id) {
        this.id = id;
    }

    public TheoGuild() {}

    public void setId(String id) {
        this.id = id;
    }
    public List<TheoUser> getUsers() {
        return TheoDataManager.getAllForGuild(id);
    }

    public String getId() {
        return id;
    }
}
