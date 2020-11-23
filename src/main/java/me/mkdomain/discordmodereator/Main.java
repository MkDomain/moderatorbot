package me.mkdomain.discordmodereator;

import me.mkdomain.discordmodereator.commands.CommandManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;

import javax.security.auth.login.LoginException;

public class Main {

    private static final CommandManager manager = new CommandManager();

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault("NzgwMTUxMzk0NDMzODkyMzgy.X7q6mg.9tlgdBmlZ5raJ1EQfZHYVRbevgw");
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.ZLIB);
        builder.setActivity(Activity.watching("8.A osztály"));
        builder.addEventListeners(manager);
        builder.build();
    }

}
