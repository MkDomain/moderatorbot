package me.mkdomain.discordmodereator.commands;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommandManager implements EventListener {

    private static final String prefix = "!";
    private final List<Command> commands = new CopyOnWriteArrayList<>();

    public CommandManager() {
        commands.add(Command.consturct(Arrays.asList("ping"), true, (name, args, event) -> {
            long time = System.currentTimeMillis();
            event.getChannel().sendMessage("Pong!").queue(resp -> resp.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
        }));
    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof PrivateMessageReceivedEvent) {
            PrivateMessageReceivedEvent e = (PrivateMessageReceivedEvent) genericEvent;
            String msg = e.getMessage().getContentRaw();
            if (!msg.startsWith(prefix)) return;
            String[] split = msg.substring(prefix.length()).split(" ");
            String[] args = new String[split.length - 1];
            System.arraycopy(split, 1, args, 0, args.length);
            String command = split[0];
            commands.stream().filter(i -> i.getAliases().contains(command)).forEach(c -> {
                if (c.allowsPrivate()) {
                    c.execute(command, args, e);
                }
            });
        }
        if (genericEvent instanceof MessageReceivedEvent) {

        }
    }
}
