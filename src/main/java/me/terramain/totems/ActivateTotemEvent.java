package me.terramain.totems;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ActivateTotemEvent implements Listener {

    @EventHandler
    public void activateTotem(EntityResurrectEvent e){
        if (e.isCancelled()) return; //true - обычная смерть | false - с тотемом

        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;

        Player player = (Player) entity;

        Totem.activateTotem( player.getInventory().getItemInMainHand(), player, ActivationType.onAction);
        Totem.activateTotem( player.getInventory().getItemInOffHand(), player, ActivationType.onAction);
    }

    @EventHandler
    public static void rightClickEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack item1 = player.getInventory().getItemInMainHand();
        ItemStack item2 = player.getInventory().getItemInOffHand();
        Totem.activateTotem(item1,player,ActivationType.rightClick);
        Totem.activateTotem(item2,player,ActivationType.rightClick);
    }


    @EventHandler
    public void minerTotem1x2BreakingEvent(BlockBreakEvent e) {
        Totem.customTotemFunctionActivate(e.getPlayer().getInventory().getItemInMainHand(), e.getPlayer(), "mineblock",new Object[]{e.getBlock().getLocation()});
        Totem.customTotemFunctionActivate(e.getPlayer().getInventory().getItemInOffHand(), e.getPlayer(), "mineblock",new Object[]{e.getBlock().getLocation()});
    }


}
