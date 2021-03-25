package me.zheroandre.bungeeutils.command.control;

import com.google.common.collect.ImmutableSet;
import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.handler.control.ControlHandler;
import me.zheroandre.bungeeutils.utils.MessageUtils;
import me.zheroandre.bungeeutils.utils.StringUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class FinishCommand extends Command implements TabExecutor {

    private final ControlHandler controlHandler;

    public FinishCommand(BungeeUtilsPlugin plugin) {
        super("finish");

        this.controlHandler = plugin.getControlHandler();

        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_CONSOLE))));
            return;
        }

        if (!sender.hasPermission("bungeeutils.finish")) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_PERMISSION))));
            return;
        }

        if (!(args.length == 1)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.COMMAND_EXAMPLE).replace("[COMMAND]", "/FINISH PLAYER"))));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

        if (target == null || !target.isConnected()) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_PLAYER_ONLINE))));
            return;
        }

        if (target.getName().equalsIgnoreCase(player.getName())) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.COMMAND_YOU_SELF))));
            return;
        }

        if (!controlHandler.getProxiedPlayers().contains(target)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.CONTROL_IS_NOT))));
            return;
        }

        controlHandler.finish(player, target);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer) || args.length != 1 || !sender.hasPermission("bungeeutils.finish")) return ImmutableSet.of();

        Set<String> matches = new HashSet<>();
        String search = args[0].toLowerCase();

        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (player.getName().toLowerCase().startsWith(search)) matches.add(player.getName());
        }

        return matches;
    }
}
