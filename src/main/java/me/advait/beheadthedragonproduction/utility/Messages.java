package me.advait.beheadthedragonproduction.utility;

import lombok.experimental.UtilityClass;
import me.advait.beheadthedragonproduction.game.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@UtilityClass
public class Messages {

    public String playerCountFraction(Arena arena) {
        return color("&#deb7ff" + arena.getActivePlayers().size() + "&r&#c785ec/&r&#deb7ff10");
    }

    /*
    Method created by Kody Simpson.
     */
    public String color(String text) {
        String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
        String[] texts = text.split(String.format(WITH_DELIMITER, "&"));

        StringBuilder finalText = new StringBuilder();

        for (int i = 0; i < texts.length; i++) {
            if (texts[i].equalsIgnoreCase("&")) {
                //get the next string
                i++;
                if (texts[i].charAt(0) == '#') {
                    finalText.append(net.md_5.bungee.api.ChatColor.of(texts[i].substring(0, 7)) + texts[i].substring(7));
                } else {
                    finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
                }
            } else {
                finalText.append(texts[i]);
            }
        }
        return finalText.toString();
    }


    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

}
