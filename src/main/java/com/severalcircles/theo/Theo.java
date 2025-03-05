package com.severalcircles.theo;

import com.severalcircles.theo.data.TheoDataManager;
import com.severalcircles.theo.events.GuildJoinEvent;
import com.severalcircles.theo.events.MessageEvent;
import com.severalcircles.theo.frontend.basic.BasedCommand;
import com.severalcircles.theo.events.SlashCommandEvent;
import com.severalcircles.theo.frontend.TheoCommand;
import com.severalcircles.theo.frontend.dev.RefreshRolesCommand;
import com.severalcircles.theo.frontend.mod.WarnCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

public class Theo {
    static final Properties properties = new Properties();
    public static String version;
    public static JDA api;
    public static final Map<String, TheoCommand> commandMap = new HashMap<>();
    public static final List<CommandData> commandDataList = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        // --- Initial Preparations ---
        InputStream is = Theo.class.getClassLoader().getResourceAsStream("version.properties");
        if (is == null) {
            throw new FileNotFoundException("version.properties not found in the classpath.");
        }
        Locale.setDefault(Locale.ENGLISH);
        properties.load(is);
        version = properties.getProperty("version");
        Logger.getGlobal().info("Theo version: " + version);
        try {
            api = JDABuilder.createDefault(System.getenv("TheoToken"))
                    .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT).build();
            api.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TheoDataManager.prepare();
        api.addEventListener(new SlashCommandEvent());
        api.addEventListener(new GuildJoinEvent());
        api.addEventListener(new MessageEvent());
        // --- Register commands ---
        commandMap.put("based", new BasedCommand());
        commandDataList.add(Commands.slash("based", "You're so based."));
        commandMap.put("refreshroles", new RefreshRolesCommand());
        commandDataList.add(Commands.slash("refreshroles", "You're refresh roles."));
        commandMap.put("warn", new WarnCommand());
        commandDataList.add(Commands.slash("warn", "Send private feedback to a user").addOption(OptionType.USER, "user", "Target user", true).addOption(OptionType.STRING, "msg", "The message to attach", true).addOption(OptionType.STRING, "subject", "Message subject", true));
        api.updateCommands()
                .addCommands(commandDataList)
                .complete();
    }
}
