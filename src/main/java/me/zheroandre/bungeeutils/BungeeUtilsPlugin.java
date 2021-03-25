package me.zheroandre.bungeeutils;

import lombok.Getter;
import me.zheroandre.bungeeutils.command.control.FinishCommand;
import me.zheroandre.bungeeutils.command.chat.ChatCommand;
import me.zheroandre.bungeeutils.command.control.ControlCommand;
import me.zheroandre.bungeeutils.command.lobby.LobbyCommand;
import me.zheroandre.bungeeutils.command.report.ReportCommand;
import me.zheroandre.bungeeutils.handler.bungee.BungeeHandler;
import me.zheroandre.bungeeutils.handler.chat.ChatHandler;
import me.zheroandre.bungeeutils.handler.control.ControlHandler;
import me.zheroandre.bungeeutils.handler.lobby.LobbyHandler;
import me.zheroandre.bungeeutils.handler.report.ReportHandler;
import me.zheroandre.bungeeutils.listener.ChatListener;
import me.zheroandre.bungeeutils.listener.PlayerDisconnectListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Arrays;

@Getter
public class BungeeUtilsPlugin extends Plugin {

    private BungeeHandler bungeeHandler;
    private ChatHandler chatHandler;
    private LobbyHandler lobbyHandler;
    private ReportHandler reportHandler;
    private ControlHandler controlHandler;

    @Override
    public void onEnable() {
        this.bungeeHandler = new BungeeHandler();
        this.chatHandler = new ChatHandler();
        this.lobbyHandler = new LobbyHandler(this);
        this.reportHandler = new ReportHandler(this);
        this.controlHandler = new ControlHandler(this);

        this.registerCommand();
        this.registerListener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommand() {
        new ChatCommand(this);
        new LobbyCommand(this);
        new ReportCommand(this);
        new ControlCommand(this);
        new FinishCommand(this);
    }

    private void registerListener() {
        Arrays.asList(
                new ChatListener(this),
                new PlayerDisconnectListener(this)
        ).forEach(listener -> this.getProxy().getPluginManager().registerListener(this, listener));
    }

}
