package me.zheroandre.bungeeutils.handler.bungee;

import me.zheroandre.bungeeutils.utils.MessageUtils;
import me.zheroandre.bungeeutils.utils.StringUtils;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeHandler {

    public void send(ProxiedPlayer player, ServerInfo server) {
        server.ping((result, error) -> {
            if (error != null) {
                player.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.SERVER_OFFLINE).replace("[SERVER]", server.getName().toUpperCase()))));
            } else {
                player.connect(server);
            }
        });
    }

    public void send(ProxiedPlayer player, ProxiedPlayer target, ServerInfo server) {
        server.ping((result, error) -> {
            if (error != null) {
                player.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.SERVER_OFFLINE).replace("[SERVER]", server.getName().toUpperCase()))));
            } else {
                player.connect(server);
                target.connect(server);
            }
        });
    }

}
