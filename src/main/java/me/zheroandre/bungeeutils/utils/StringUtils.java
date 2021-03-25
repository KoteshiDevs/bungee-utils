package me.zheroandre.bungeeutils.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StringUtils {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String color(String string, char color) {
        char[] colorChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'k', 'l', 'm', 'n', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] stringChars = string.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();

        for (char c : colorChars) {
            string = string.replace("&" + c, "");
        }

        for (char c : stringChars) {
            stringBuilder.append("&").append(color).append(c).append("&").append(color);
        }

        return stringBuilder.toString();
    }

    public static String transform(String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {
            stringBuilder.append(color(string)).append(" ");
        }

        return stringBuilder.toString();
    }

    public void Porco(ProxiedPlayer target) {

        for (int i = 0; i < 10; i++) {
            target.sendMessage(new TextComponent(" Questo è il giro: " + i));
        }

    }

}
