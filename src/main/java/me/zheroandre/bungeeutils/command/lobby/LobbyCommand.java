package me.zheroandre.bungeeutils.command.lobby;

import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.handler.bungee.BungeeHandler;
import me.zheroandre.bungeeutils.handler.control.ControlHandler;
import me.zheroandre.bungeeutils.handler.lobby.LobbyHandler;
import me.zheroandre.bungeeutils.utils.MessageUtils;
import me.zheroandre.bungeeutils.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command {

    private final BungeeHandler bungeeHandler;
    private final LobbyHandler lobbyHandler;
    private final ControlHandler controlHandler;

    public LobbyCommand(BungeeUtilsPlugin plugin) {
        super("lobby", null, "hub");

        this.bungeeHandler = plugin.getBungeeHandler();
        this.lobbyHandler = plugin.getLobbyHandler();
        this.controlHandler = plugin.getControlHandler();

        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_CONSOLE))));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.getServer().getInfo().getName().equals("lobby-1")) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.SERVER_ALREADY).replace("[SERVER]", player.getServer().getInfo().getName().toUpperCase()))));
            return;
        }

        if (controlHandler.getProxiedPlayers().contains(player)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.COMMAND_DENIED))));
            return;
        }

        bungeeHandler.send(player, lobbyHandler.getServer());
    }
}
