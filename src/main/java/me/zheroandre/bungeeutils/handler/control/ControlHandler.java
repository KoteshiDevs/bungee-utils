package me.zheroandre.bungeeutils.handler.control;

import lombok.Getter;
import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.handler.bungee.BungeeHandler;
import me.zheroandre.bungeeutils.handler.lobby.LobbyHandler;
import me.zheroandre.bungeeutils.utils.MessageUtils;
import me.zheroandre.bungeeutils.utils.StringUtils;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class ControlHandler {

    private final BungeeHandler bungeeHandler;
    private final LobbyHandler lobbyHandler;

    @Getter private final ServerInfo server;
    @Getter private final List<ProxiedPlayer> proxiedPlayers = new ArrayList<>();

    public ControlHandler(BungeeUtilsPlugin plugin) {
        this.server = plugin.getProxy().getServerInfo("control");

        this.bungeeHandler = plugin.getBungeeHandler();
        this.lobbyHandler = plugin.getLobbyHandler();
    }

    public void send(ProxiedPlayer player, ProxiedPlayer target) {
        this.server.ping((result, error) -> {
            if (error != null) {
                player.sendMessage(new TextComponent(StringUtils.color(MessageUtils.message(MessageUtils.ErrorMessage.SERVER_OFFLINE).replace("[SERVER]", server.getName().toUpperCase()))));
            } else {
                this.proxiedPlayers.add(target);
                player.connect(server);
                target.connect(server);
            }
        });
    }

    public void finish(ProxiedPlayer player, ProxiedPlayer target) {
        this.proxiedPlayers.remove(target);
        bungeeHandler.send(player, target, lobbyHandler.getServer());
    }

}
