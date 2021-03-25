package me.zheroandre.bungeeutils.handler.report;

import lombok.Getter;
import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.utils.MessageUtils;
import me.zheroandre.bungeeutils.utils.StringUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReportHandler {

    private final BungeeUtilsPlugin plugin;

    @Getter private final List<ProxiedPlayer> timerReports = new ArrayList<>();

    public ReportHandler(BungeeUtilsPlugin plugin) {
        this.plugin = plugin;
    }

    public void timer(ProxiedPlayer player) {
        plugin.getProxy().getScheduler().schedule(plugin, () -> {
            this.timerReports.remove(player);
        }, 30, TimeUnit.SECONDS);
    }

    public void send(ProxiedPlayer player, ProxiedPlayer target, String reason) {
        this.timerReports.add(player);

        player.sendMessage(
                new TextComponent(
                        StringUtils.color(
                                MessageUtils.message(MessageUtils.SuccessMessage.REPORT_SUCCESS)
                                    .replace("[TARGET]", target.getName())
                        )
                )
        );

        TextComponent textComponent = new TextComponent(
                StringUtils.color(
                        MessageUtils.message(MessageUtils.SuccessMessage.REPORT_SUCCESS_ALERT)
                                .replace("[PLAYER]" , player.getName())
                                .replace("[TARGET]", target.getName())
                                .replace("[SERVER]", player.getServer().getInfo().getName().toUpperCase())
                )
        );
        textComponent.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                StringUtils.color(MessageUtils.message(MessageUtils.SuccessMessage.REPORT_REASON)).replace("[REASON]", reason)
        ).create()));

        for (ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
            if (proxiedPlayer.hasPermission("bungeeutils.report.recive")) {
                proxiedPlayer.sendMessage(textComponent);
            }
        }

        this.timer(player);
    }

}
