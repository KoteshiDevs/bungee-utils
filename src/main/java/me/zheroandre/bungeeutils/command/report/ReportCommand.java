package me.zheroandre.bungeeutils.command.report;

import com.google.common.collect.ImmutableSet;
import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.handler.report.ReportHandler;
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

public class ReportCommand extends Command implements TabExecutor {

    private final ReportHandler reportHandler;

    public ReportCommand(BungeeUtilsPlugin plugin) {
        super("report");

        this.reportHandler = plugin.getReportHandler();

        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_CONSOLE))));
            return;
        }

        if (!sender.hasPermission("bungeeutils.report.send")) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.NO_PERMISSION))));
            return;
        }

        if (!(args.length >= 2)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.COMMAND_EXAMPLE).replace("[COMMAND]", "/REPORT PLAYER REASON"))));
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

        if (reportHandler.getTimerReports().contains(player)) {
            sender.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.COMMAND_TIMER).replace("[TIME]", "30 SECONDI"))));
            return;
        }

        String[] strings = new String[args.length - 1];
        System.arraycopy(args, 1, strings, 0, args.length - 1);

        reportHandler.send(player, target, StringUtils.transform(strings).toUpperCase());
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer) || args.length != 1 || !sender.hasPermission("bungeeutils.report.send")) return ImmutableSet.of();

        Set<String> matches = new HashSet<>();
        String search = args[0].toLowerCase();

        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (player.getName().toLowerCase().startsWith(search)) matches.add(player.getName());
        }

        return matches;
    }
}
