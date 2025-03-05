package com.severalcircles.theo.frontend;

import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class TheoEmbed {
    public ResourceBundle local;
    public TheoEmbed(Locale locale) {
        local = ResourceBundle.getBundle(this.getClass().getName().replace(".", "/"), locale);
    }
    public abstract MessageEmbed get();
}
