package com.severalcircles.theo.frontend;

import com.severalcircles.theo.data.user.TheoUser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface TheoCommand {
    public void execute(SlashCommandInteractionEvent event, TheoUser user);
}
