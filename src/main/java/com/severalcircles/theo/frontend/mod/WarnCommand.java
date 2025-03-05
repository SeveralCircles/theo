package com.severalcircles.theo.frontend.mod;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.TheoGuild;
import com.severalcircles.theo.data.user.TheoUser;
import com.severalcircles.theo.data.user.UserWarning;
import com.severalcircles.theo.frontend.TheoCommand;
import com.severalcircles.theo.frontend.msg.NotAllowedEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WarnCommand implements TheoCommand {
    @Override
    public void execute(SlashCommandInteractionEvent event, TheoUser user) {
        TheoGuild guild = TheoDataManager.getGuild(event.getGuild().getId());
        if (!user.hasPermission(event.getMember(), new WarnCommand())){
            event.replyEmbeds(new NotAllowedEmbed(Locale.forLanguageTag(user.getLocale()), event.getUser()).get()).queue();
            return;
        }
        User target = event.getOption("user").getAsUser();
        TheoUser targetUser = TheoDataManager.getUser(target.getId(), event.getGuild().getId());
        if (targetUser == null) {
            targetUser = new TheoUser(target.getId(), event.getGuild().getId(), new ArrayList<>(), "en-US");
            TheoDataManager.saveUser(targetUser);
        }
        UserWarning userWarning = new UserWarning(new Date(), user, targetUser, event.getOption("msg").getAsString(), null, event.getOption("subject").getAsString(), event.getGuild().getId());
        event.reply("ok lol").setEphemeral(true).queue();
        target.openPrivateChannel().complete().sendMessageEmbeds(new PrivateWarningEmbed(Locale.forLanguageTag(targetUser.getLocale()), userWarning).get()).queue();
    }
}
