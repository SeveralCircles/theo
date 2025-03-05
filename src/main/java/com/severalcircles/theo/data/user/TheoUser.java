package com.severalcircles.theo.data.user;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.TheoGuild;
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
    public TheoUser(String id, String guildId, List<UserWarning> warnings, String locale) {
        this.id = id;
        this.guildId = guildId;
        this.warnings = warnings;
        this.locale = locale;
    }

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

    public boolean hasPermission(Member user, TheoCommand command) {
        AtomicBoolean hasPermission = new AtomicBoolean(false);
        TheoGuild guild = TheoDataManager.getGuild(user.getGuild().getId());
        if (guild.getPermissions() == null) guild.setPermissions(new HashMap<>());
        user.getRoles().forEach(role -> {
            List<String> permissionsForRole = guild.getPermissions().get(role.getId());
            if (permissionsForRole != null) {
                permissionsForRole.forEach(perm -> {
                    if (command.getClass().getName().startsWith(perm)) hasPermission.set(true);
                });
            }
        });
        return hasPermission.get();
    }
}
