package me.mkdomain.discordmodereator.commands;

import me.mkdomain.discordmodereator.special.TriConsumer;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public abstract class Command {

    public abstract List<String> getAliases();

    public abstract void execute(String name, String[] args, MessageReceivedEvent event);

    public abstract boolean allowsPrivate();

    public static Command consturct(final List<String> aliases, final boolean allowsPrivate, final TriConsumer<String, String[], MessageReceivedEvent> exec) {
        return new Command() {
            @Override
            public final List<String> getAliases() {
                return aliases;
            }

            @Override
            public final void execute(String name, String[] args, MessageReceivedEvent event) {
                exec.accept(name, args, event);
            }

            @Override
            public final boolean allowsPrivate() {
                return allowsPrivate;
            }
        };
    }

}
