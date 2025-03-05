package com.severalcircles.theo.data;

import com.severalcircles.theo.data.user.TheoUser;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class TheoDataManager {
    public static final File THEO_DIR = new File(System.getProperty("user.dir") + "/theo");
    public static final File USER_DIR = new File(THEO_DIR.getAbsolutePath() + "/user");
    public static final File GUILD_DIR = new File(THEO_DIR.getAbsolutePath() + "/guild");
    public static void prepare() {
        THEO_DIR.mkdirs();
        USER_DIR.mkdirs();
    }
    public static void saveUser(TheoUser user) {
        File userFile = new File(USER_DIR.getAbsolutePath() + "/" + user.getGuildId() + "/" + user.getId() + ".yml");
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(userFile)) {
            userFile.createNewFile();
            yaml.dump(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TheoUser getUser(String id, String guildId) throws IOException {
        File userFile = new File(USER_DIR.getAbsolutePath() + "/" + guildId + "/" + id + ".yml");
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(userFile)) {
            return yaml.loadAs(reader, TheoUser.class);
        } catch (FileNotFoundException e) {
            try {
                userFile.getParentFile().mkdirs();
                userFile.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            return new TheoUser(id,guildId, new LinkedList<>(), Locale.getDefault().toLanguageTag());
        }
    }
    public static List<TheoUser> getAllForGuild(String guildId) {
        List<TheoUser> userList = new ArrayList<>();
        File[] files = new File(USER_DIR.getAbsolutePath() + "/" + guildId).listFiles();
        if (files != null) {
            for (File file : files) {
                Yaml yaml = new Yaml();
                try (FileReader reader = new FileReader(file)) {
                    userList.add(yaml.loadAs(reader, TheoUser.class));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return userList;
    }
    public static void saveGuild(TheoGuild guild) {
        File guildFile = new File(GUILD_DIR.getAbsolutePath() + "/" + guild.getId() + ".yml");
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(guildFile)) {
            guildFile.createNewFile();
            yaml.dump(guild, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TheoGuild getGuild(String id) {
        File guildFile = new File(GUILD_DIR.getAbsolutePath() + "/" + id + ".yml");
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(guildFile)) {
            return yaml.loadAs(reader, TheoGuild.class);
        } catch (FileNotFoundException e) {
            try {
                guildFile.getParentFile().mkdirs();
                guildFile.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            return new TheoGuild(id, new HashMap<>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
