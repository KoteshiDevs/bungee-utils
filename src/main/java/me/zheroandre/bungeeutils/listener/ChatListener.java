package me.zheroandre.bungeeutils.listener;

import me.zheroandre.bungeeutils.BungeeUtilsPlugin;
import me.zheroandre.bungeeutils.handler.chat.ChatHandler;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    private final ChatHandler chatHandler;

    public ChatListener(BungeeUtilsPlugin plugin) {
        this.chatHandler = plugin.getChatHandler();
    }

    @EventHandler
    public void chatEvent(ChatEvent e) {
        ProxiedPlayer player = (ProxiedPlayer) e.getSender();
        String message = e.getMessage();

        if (!chatHandler.getProxiedPlayers().contains(player)) return;
        if (message.startsWith("/")) return;

        e.setCancelled(true);
        chatHandler.send(player, message);
    }

}
