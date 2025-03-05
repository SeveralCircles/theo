package com.severalcircles.theo.frontend.msg;

import com.severalcircles.theo.frontend.TheoEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Locale;

public class GenericExceptionEmbed extends TheoEmbed {
    Exception e;
    public GenericExceptionEmbed(Locale locale, Exception e) {
        super(locale);
        this.e = e;
    }

    @Override
    public MessageEmbed get() {
        return new EmbedBuilder()
                .setAuthor(local.getString("author"))
                .setTitle(local.getString("title"))
                .setDescription(local.getString("description"))
                .addField(local.getString("exception"), e.getClass().getName(), true)
                .addField(local.getString("message"), e.getMessage(), true)
                .setColor(Color.RED)
                .build();
    }
}
