package com.severalcircles.theo.frontend.basic;

import com.severalcircles.theo.data.user.TheoUser;
import com.severalcircles.theo.frontend.TheoCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Locale;

public class BasedCommand implements TheoCommand {
    @Override
    public void execute(SlashCommandInteractionEvent event, TheoUser user) {
        event.replyEmbeds(new BasedEmbed(Locale.getDefault()).get()).queue();
    }
}
