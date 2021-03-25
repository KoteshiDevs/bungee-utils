package me.zheroandre.bungeeutils.handler.lobby;

import lombok.Getter;
import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import net.md_5.bungee.api.config.ServerInfo;

public class LobbyHandler {

    @Getter private final ServerInfo server;

    public LobbyHandler(BungeeUtilsPlugin plugin) {
        this.server = plugin.getProxy().getServerInfo("lobby-1");
    }

}
