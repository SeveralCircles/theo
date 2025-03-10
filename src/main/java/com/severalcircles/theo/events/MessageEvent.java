package com.severalcircles.theo.events;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.guild.TheoGuild;
import com.severalcircles.theo.data.user.TheoUser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.lang.reflect.Member;

public class MessageEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        TheoUser user = null;
        try {
            user = TheoDataManager.getUser(event.getAuthor().getId(), event.getGuild().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException ignored) {}
        TheoGuild g = TheoDataManager.getGuild(event.getGuild().getId());


        if (g != null) TheoDataManager.saveGuild(g);
        else TheoDataManager.saveGuild(new TheoGuild(user.getGuildId()));
        if (user != null) TheoDataManager.saveUser(user);
        else TheoDataManager.saveUser(new TheoUser());
        super.onMessageReceived(event);
    }
}
