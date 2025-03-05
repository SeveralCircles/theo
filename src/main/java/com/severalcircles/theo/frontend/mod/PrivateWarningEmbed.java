package com.severalcircles.theo.frontend.mod;

import com.severalcircles.theo.Theo;
import com.severalcircles.theo.data.user.UserWarning;
import com.severalcircles.theo.frontend.TheoEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
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
        Guild guild = Theo.api.getGuildById(userWarning.getGuildId());
        String guildName = guild.getName();
        return new EmbedBuilder()
                .setAuthor(String.format(local.getString("author"), guildName), null, guild.getIconUrl())
                .setTitle(local.getString("title"))
                .setDescription(String.format(local.getString("description"), guildName))
                .addField(local.getString("subject"), userWarning.getSubject(), false)
                .addField(local.getString("reason"), userWarning.getMsg(), false)
                .addField(local.getString("whooSees.title"), local.getString("whooSees.description"), true)
                .addField(local.getString("whoDoesnt.title"), local.getString("whoDoesnt.description"), true)
                .setThumbnail("https://severalcircles.com/theo/assets/icon/warning.png")
                .setColor(Color.YELLOW)
                .build();
    }
}
