package com.example.socialtexting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SocialTextingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("SocialTexting включён!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эта команда только для игроков.");
            return true;
        }
        Player player = (Player) sender;

        // Справка при /st без аргументов
        if (args.length == 0) {
            player.sendMessage("§4[Social Texting] §rДоступные режимы:");
            player.sendMessage("");
            player.sendMessage("Режим P (оскорбление):");
            player.sendMessage("  /st p [rus/eng] [ник] [имя]");
            player.sendMessage("  → Фиксирует, что [ник] назван [имя].");
            player.sendMessage("");
            player.sendMessage("Режим M (старый):");
            player.sendMessage("  /st m [rus/eng] [ник] [причина]");
            player.sendMessage("  • Если [ник] чужой → [причина] должна быть ЧИСЛОМ.");
            player.sendMessage("  • Если [ник] твой → [причина] может быть ЛЮБЫМ ТЕКСТОМ.");
            player.sendMessage("");
            player.sendMessage("Примеры:");
            player.sendMessage("  /st p rus Alex Вася");
            player.sendMessage("  /st m eng Bob спам 5");
            player.sendMessage("  /st m rus Я спам Привет");
            return true;
        }

        return STCommand.processSTCommand(player, args);
    }
}
