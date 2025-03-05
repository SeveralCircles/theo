package com.severalcircles.theo.events;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.TheoGuild;
import com.severalcircles.theo.data.user.TheoUser;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class GuildJoinEvent extends ListenerAdapter {
    @Override
    public void onGuildJoin(net.dv8tion.jda.api.events.guild.GuildJoinEvent event) {
        TheoGuild g = TheoDataManager.getGuild(event.getGuild().getId());
        TheoDataManager.saveGuild(g);
        event.getGuild().getMembers().forEach(member -> {
            TheoUser user = null;
            try {
                user = TheoDataManager.getUser(member.getId(), event.getGuild().getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TheoDataManager.saveUser(user);
        });
        super.onGuildJoin(event);
    }
    public void listRoles(TheoGuild guild, Guild discordGuild) {
        for (Role role : discordGuild.getRoles()) {
            // Inherit mod commands from manage messages permission
            if (role.hasPermission(Permission.MESSAGE_MANAGE)) guild.addPermission(role.getId(), "com.severalcircles.theo.frontend.mod");

        }
    }
}
