package me.zheroandre.bungeeutils.command.chat;

import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.handler.chat.ChatHandler;
import me.zheroandre.bungeeutils.utils.MessageUtils;
import me.zheroandre.bungeeutils.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ChatCommand extends Command {

    private final ChatHandler chatHandler;

    public ChatCommand(BungeeUtilsPlugin plugin) {
        super("staffchat", null, "sc");

        this.chatHandler = plugin.getChatHandler();

        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_CONSOLE))));
            return;
        }

        if (!sender.hasPermission("bungeeutils.staffchat")) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_PERMISSION))));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length != 0) {
            chatHandler.send(player, StringUtils.transform(args));
            return;
        }

        if (chatHandler.getProxiedPlayers().contains(player)) {
            chatHandler.getProxiedPlayers().remove(player);
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.SuccessMessage.CHAT_DISABLE))));
        } else {
            chatHandler.getProxiedPlayers().add(player);
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.SuccessMessage.CHAT_ENABLE))));
        }
    }

}
