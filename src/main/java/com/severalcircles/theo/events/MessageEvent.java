package com.severalcircles.theo.events;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.data.TheoGuild;
import com.severalcircles.theo.data.user.TheoUser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        TheoUser user = TheoDataManager.getUser(event.getAuthor().getId(), event.getGuild().getId());
        TheoGuild g = TheoDataManager.getGuild(event.getGuild().getId());

        TheoDataManager.saveGuild(g);
        TheoDataManager.saveUser(user);
        super.onMessageReceived(event);
    }
}
