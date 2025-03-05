package com.severalcircles.theo.events;

import com.severalcircles.theo.Theo;
import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.TheoGuild;
import com.severalcircles.theo.data.user.TheoUser;
import com.severalcircles.theo.frontend.msg.GenericExceptionEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class SlashCommandEvent extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) {
            event.reply("For now, only human users can use Theo.").queue();
        }
        TheoUser user;
        try {
            user = TheoDataManager.getUser(event.getUser().getId(), event.getGuild().getId());
        } catch (NullPointerException e) {
            event.reply("For now, you can only use Theo within a server.").queue();
            return;
        } catch (Exception e) {
            event.replyEmbeds(new GenericExceptionEmbed(Locale.forLanguageTag("en"), e).get()).queue();
            return;
        }
        Theo.commandMap.forEach((k, v) -> {
            if (event.getFullCommandName().equals(k))  {
                try {v.execute(event, user);}
                catch (Exception e) {
                    event.replyEmbeds(new GenericExceptionEmbed(Locale.forLanguageTag(user.getLocale()), e).get()).queue();
                }
            }});
        super.onSlashCommandInteraction(event);
        TheoGuild g = TheoDataManager.getGuild(event.getGuild().getId());

        TheoDataManager.saveGuild(g);
        TheoDataManager.saveUser(user);
        if (!event.isAcknowledged()) event.reply("Sorry, looks like something didn't work out. Try again?").queue();
    }
}
