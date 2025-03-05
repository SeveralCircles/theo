package com.severalcircles.theo.frontend.msg;

import com.severalcircles.theo.frontend.TheoEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Locale;

public class NotAllowedEmbed extends TheoEmbed {
    User user;
    public NotAllowedEmbed(Locale locale, User user) {
        super(locale);
        this.user = user;

    }

    @Override
    public MessageEmbed get() {
        return new EmbedBuilder()
                .setAuthor(local.getString("author"))
                .setTitle(String.format(local.getString("title"), user.getGlobalName()))
                .setDescription(local.getString("description"))
                .setColor(Color.RED)
                .build();
    }
}
