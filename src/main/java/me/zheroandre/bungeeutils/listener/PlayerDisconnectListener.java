package me.zheroandre.bungeeutils.listener;

import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.handler.control.ControlHandler;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener implements Listener {

    private final ControlHandler controlHandler;

    public PlayerDisconnectListener(BungeeUtilsPlugin plugin) {
        this.controlHandler = plugin.getControlHandler();
    }

    @EventHandler
    public void playerDisconnectEvent(PlayerDisconnectEvent e) {
        ProxiedPlayer player = e.getPlayer();

        if (!controlHandler.getProxiedPlayers().contains(player)) return;

        controlHandler.getProxiedPlayers().remove(player);
    }

}
