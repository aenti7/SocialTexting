package com.example.socialtexting;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class STCommand implements CommandExecutor {
    private final SocialTextingPlugin plugin;

    public STCommand(SocialTextingPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Только игроки могут использовать эту команду!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("/st [rus|eng] [ник] [причина] [номер]/[сообщение]");
            player.sendMessage("Пример: /st rus Alex спам 5/Привет");
            return true;
        }

        if (args.length < 4) {
            player.sendMessage("Неверный формат. Нужно 4 аргумента после /st.");
            return true;
        }

        return processSTCommand(player, args);
    }

    private boolean processSTCommand(Player commandSender, String[] args) {
        String language = args[0].toLowerCase();
        String targetPlayer = args[1];
        String reason = args[2];
        String messageInfo = args[3];

        if (!"rus".equals(language) && !"eng".equals(language)) {
            commandSender.sendMessage("Первый аргумент должен быть 'rus' или 'eng'.");
            return true;
        }

        String[] parts = messageInfo.split("/", 2);
        if (parts.length != 2) {
            commandSender.sendMessage("Формат: [номер]/[текст]");
            return true;
        }

        String firstPart = parts[0];
        String actualMessage = parts[1];
        String displayText;

        // Если ник совпадает с твоим — считаем firstPart текстом
        if (targetPlayer.equalsIgnoreCase(commandSender.getName())) {
            displayText = "сообщение «" + firstPart + "»";
        } else {
            // Если чужой ник — первый аргумент должен быть числом
            if (isPositiveInteger(firstPart)) {
                displayText = "сообщение под номером " + firstPart;
            } else {
                commandSender.sendMessage("Для чужого ника первый аргумент должен быть числом!");
                return true;
            }
        }

        String finalMessage;
        if ("rus".equals(language)) {
            finalMessage = String.format("|||||||||| ST: Сообщение игрока %s было распознано как %s (%s). Не волнуйтесь, автор уже депортирован в Азкабан. ST работает в штатном режиме! ||||||||||",
                    targetPlayer, reason, displayText);
        } else {
            finalMessage = String.format("|||||||||| ST: %s's message was recognised as %s (%s). Don't worry, the author was deported to Azkaban. ST is operating normally! ||||||||||",
                    targetPlayer, reason, displayText);
        }

        plugin.getServer().broadcastMessage(finalMessage);
        commandSender.sendMessage("Отправлено в чат: " + actualMessage);
        return true;
    }

    private boolean isPositiveInteger(String str) {
        try {
            int value = Integer.parseInt(str);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
          }
