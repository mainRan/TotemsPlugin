package me.terramain.totems;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CMD implements TabExecutor {
    public CMD(){
        Main.plugin.getCommand("givetotem").setExecutor(this::onCommand);
        Main.plugin.getCommand("givetotem").setTabCompleter(this::onTabComplete);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        Totem totem = null;

        StringBuilder totemName = new StringBuilder();
        for (String arg : args) {
            totemName.append(arg+" ");
        }
        totem = Totem.getTotemFromName(totemName.toString().trim());
        if (totem == null){
            sender.sendMessage("Такой тотем не найден");
            return true;
        }

        player.getInventory().addItem(totem.toItem());
        sender.sendMessage("вам выдан " + totem.name);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1){
            List<String> returnWords = new ArrayList<>();
            for (Totem totem : Totem.totemList) {
                returnWords.add(totem.name);
            }
            return returnWords;
        }
        return new ArrayList<>();
    }
}
