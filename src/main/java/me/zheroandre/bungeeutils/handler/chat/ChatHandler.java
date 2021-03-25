package me.zheroandre.bungeeutils.handler.chat;

import lombok.Getter;
import me.zheroandre.bungeeutils.utils.MessageUtils;
import me.zheroandre.bungeeutils.utils.StringUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class ChatHandler {

    @Getter private final List<ProxiedPlayer> proxiedPlayers = new ArrayList<>();

    public void send(ProxiedPlayer player, String string) {
        for (ProxiedPlayer proxiedPlayer : ProxyServer.getInstance().getPlayers()) {
            if (proxiedPlayer.hasPermission("bungeeutils.staffchat.recive")) {
                proxiedPlayer.sendMessage(
                        new TextComponent(
                                StringUtils.color(
                                        MessageUtils.message(MessageUtils.SuccessMessage.CHAT_FORMAT)
                                                .replace("[SERVER]", player.getServer().getInfo().getName().toUpperCase())
                                                .replace("[PLAYER]", player.getName())
                                                .replace("[MESSAGE]", StringUtils.color(string, '7'))
                                )
                        )
                );
            }
        }
    }

}
