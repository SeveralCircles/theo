package com.severalcircles.theo.frontend.mod;

import com.severalcircles.theo.Theo;
import com.severalcircles.theo.data.user.UserWarning;
import com.severalcircles.theo.frontend.TheoEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Locale;

public class PrivateWarningEmbed extends TheoEmbed {
    UserWarning userWarning;
    public PrivateWarningEmbed(Locale locale, UserWarning warning) {
        super(locale);
        this.userWarning = warning;
    }

    @Override
    public MessageEmbed get() {
        String guildName = Theo.api.getGuildById(userWarning.getGuildId()).getName();
        return new EmbedBuilder()
                .setAuthor(String.format(local.getString("author"), guildName))
                .setTitle(local.getString("title"))
                .setDescription(String.format(local.getString("description"), guildName))
                .addField(local.getString("subject"), userWarning.getSubject(), false)
                .addField(local.getString("reason"), userWarning.getMsg(), false)
                .addField(local.getString("whooSees.title"), local.getString("whooSees.description"), true)
                .addField(local.getString("whoDoesnt.title"), local.getString("whoDoesnt.description"), true)
                .setColor(Color.YELLOW)
                .build();
    }
}
