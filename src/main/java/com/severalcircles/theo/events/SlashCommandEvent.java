package com.severalcircles.theo.events;

import com.severalcircles.theo.Theo;
import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.TheoGuild;
import com.severalcircles.theo.data.user.TheoUser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandEvent extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        TheoUser user = TheoDataManager.getUser(event.getUser().getId(), event.getGuild().getId());
        Theo.commandMap.forEach((k, v) -> {
            if (event.getFullCommandName().equals(k))  v.execute(event, user);});
        super.onSlashCommandInteraction(event);
        TheoGuild g = TheoDataManager.getGuild(event.getGuild().getId());

        TheoDataManager.saveGuild(g);
        TheoDataManager.saveUser(user);
    }
}
