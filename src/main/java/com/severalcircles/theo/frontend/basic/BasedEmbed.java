package com.severalcircles.theo.frontend.basic;

import com.severalcircles.theo.frontend.TheoEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.Locale;

public class BasedEmbed extends TheoEmbed {
    public BasedEmbed(Locale locale) {
        super(locale);
    }

    @Override
    public MessageEmbed get() {
        return new EmbedBuilder()
                .setTitle(local.getString("title"))
                .setDescription(local.getString("description"))
                .setColor(Color.orange)
                .build();
    }
}
