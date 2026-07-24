package com.example.st;

import org.bukkit.Bukkit;
import org.bukkit.Player;

public class STCommand {

    public static boolean processSTCommand(Player commandSender, String[] args) {
        // Минимум 4 аргумента: /st [p/m] [rus/eng] [ник] [аргумент]
        if (args.length < 4) {
            commandSender.sendMessage("Неверный формат. Напиши просто /st, чтобы увидеть примеры.");
            return true;
        }

        String mode = args[0].toLowerCase();
        String language = args[1].toLowerCase();
        String targetPlayer = args[2];
        String argument = args[3];

        String senderName = commandSender.getName();
        String finalMessage;

        if ("p".equals(mode)) {
            // Режим P: оскорбление
            if ("rus".equals(language)) {
                finalMessage = "|||||||||| §4§lST§r: " +
                        "§l" + senderName + "§r обозвал " +
                        "§l" + targetPlayer + "§r как " +
                        "\"" + argument + "\". " +
                        "Азкабан проверяет данное оскорбление… " +
                        "§4§lДЕПОРТИРОВАТЬ В АЗКАБАН!!!§r " +
                        "ST работает в штатном режиме! ||||||||||";
            } else {
                finalMessage = "|||||||||| §4§lST§r: " +
                        "§l" + senderName + "§r called " +
                        "§l" + targetPlayer + "§r a \"" + argument + "\". " +
                        "Azkaban is checking… " +
                        "§4§lDEPORT TO AZKABAN!!!§r " +
                        "ST operates normally! ||||||||||";
            }
            Bukkit.broadcastMessage(finalMessage);
            return true;

        } else if ("m".equals(mode)) {
            String displayText;

            if (targetPlayer.equalsIgnoreCase(senderName)) {
                // Свой ник: аргумент — это просто текст
                if ("rus".equals(language)) {
                    displayText = "сообщение «" + argument + "»";
                } else {
                    displayText = "message \"" + argument + "\"";
                }
            } else {
                // Чужой ник: аргумент должен быть натуральным числом
                try {
                    int number = Integer.parseInt(argument);
                    if (number <= 0) throw new NumberFormatException();
                    if ("rus".equals(language)) {
                        displayText = "сообщение под номером " + number;
                    } else {
                        displayText = "message number " + number;
                    }
                } catch (NumberFormatException e) {
                    commandSender.sendMessage("Для чужого ника аргумент должен быть натуральным числом!");
                    return true;
                }
            }

            String reason = argument;

            if ("rus".equals(language)) {
                finalMessage = "|||||||||| §4§lST§r: " +
                        "Сообщение игрока " +
                        "§l" + targetPlayer + "§r было распознано как " +
                        "§l" + reason + "§r (" + displayText + "). " +
                        "Не волнуйтесь, автор такого контента уже был депортирован с сервера в Азкабан для отбывания срока за свои слова. " +
                        "ST работает в штатном режиме! ||||||||||";
            } else {
                finalMessage = "|||||||||| §4§lST§r: " +
                        "§l" + targetPlayer + "§r's message was recognised as " +
                        "§l" + reason + "§r (" + displayText + "). " +
                        "Don't worry, the author of this content has already been deported from the server to Azkaban to cancel their terms of service. " +
                        "ST operates normally! ||||||||||";
            }

            Bukkit.broadcastMessage(finalMessage);
            return true;

        } else {
            commandSender.sendMessage("Первый аргумент должен быть 'p' (оскорбление) или 'm' (старый режим).");
            return true;
        }
    }
}
