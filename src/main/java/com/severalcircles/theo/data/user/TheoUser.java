package com.severalcircles.theo.data.user;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.guild.TheoGuild;
import com.severalcircles.theo.frontend.TheoCommand;
import net.dv8tion.jda.api.entities.Member;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TheoUser {
    String id;
    String guildId;
    List<UserWarning> warnings;
    String locale;
//    PermissionLevel permissionLevel;

    public TheoUser(String id, String guildId, List<UserWarning> warnings, String locale) {
        this.id = id;
        this.guildId = guildId;
        this.warnings = warnings;
        this.locale = locale;
    }

//    public PermissionLevel getPermissionLevel() {
//        return permissionLevel;
//    }

//    public void setPermissionLevel(PermissionLevel permissionLevel) {
//        this.permissionLevel = permissionLevel;
//    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public TheoUser() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public List<UserWarning> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<UserWarning> warnings) {
        this.warnings = warnings;
    }

    public void addWarning(UserWarning warning) {
        warnings.add(warning);
    }

}
