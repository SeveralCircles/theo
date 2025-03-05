package com.severalcircles.theo.frontend.dev;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.TheoGuild;
import com.severalcircles.theo.data.user.TheoUser;
import com.severalcircles.theo.events.GuildJoinEvent;
import com.severalcircles.theo.frontend.TheoCommand;
import com.severalcircles.theo.frontend.msg.NotAllowedEmbed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Locale;

public class RefreshRolesCommand implements TheoCommand {
    @Override
    public void execute(SlashCommandInteractionEvent event, TheoUser user) {
        TheoGuild theoGuild = TheoDataManager.getGuild(user.getGuildId());
        if (user.hasPermission(event.getMember(), this) | event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            new GuildJoinEvent().listRoles(theoGuild, event.getGuild());
            event.reply("Done!").queue();
            theoGuild.getPermissions().forEach((k,v) -> {
                System.out.println("Key: " + k + ", Value: " + v);
                v.forEach(va -> {
                    System.out.println("Value: " + va);
                });
            });
            TheoDataManager.saveGuild(theoGuild);
        } else event.replyEmbeds(new NotAllowedEmbed(Locale.forLanguageTag(user.getLocale()), event.getUser()).get()).queue();
    }
}
